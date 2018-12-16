package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private boolean open;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public Door( Orientation orientation) {
        super("door");
        if (orientation == Orientation.HORIZONTAL){
            this.setAnimation(new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
        else{
            this.setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
        this.open = false;
    }

    public Door(String name, Orientation orientation) {
        super(name);
        if (orientation == Orientation.HORIZONTAL){
            this.setAnimation(new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
        else{
             this.setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));
        }
        this.open = false;
    }

    @Override
    public void useWith(Actor actor) {
        if(actor.intersects(this)){
            if (this.isOpen()){
                this.close();
            }
            else {
                this.open();
            }
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.close();
    }

    @Override
    public void open() {
        if (this.getScene() != null){
            this.getAnimation().setPlayMode(Animation.PlayMode.ONCE);
            this.getAnimation().play();
            int doorX,doorY;
            if(this.getWidth() > this.getHeight()){
                doorX = (this.getPosX()) / (this.getWidth() / 2);
            }
            else{
                doorX = (this.getPosX()) / (this.getWidth());
            }
            if(this.getHeight() > this.getWidth()){
                doorY = (this.getPosY()) / (this.getHeight() / 2);
            }
            else{
                doorY = (this.getPosY()) / (this.getHeight());
            }
            this.getScene().getMap().getTile(doorX, doorY).setType(MapTile.Type.CLEAR);
            if (this.getHeight() > this.getWidth()) {
                this.getScene().getMap().getTile(doorX, doorY + 1).setType(MapTile.Type.CLEAR);
            }
            else {
                this.getScene().getMap().getTile(doorX + 1, doorY).setType(MapTile.Type.CLEAR);
            }
            this.open = true;
            this.getScene().getMessageBus().publish(DOOR_OPENED, this);
        }
    }

    @Override
    public void close() {
        if (this.getScene() != null) {
            this.getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
            this.getAnimation().play();
            int doorX,doorY;
            if(this.getWidth() > this.getHeight()){
                doorX = (this.getPosX()) / (this.getWidth() / 2);
            }
            else{
                doorX = (this.getPosX()) / (this.getWidth());
            }
            if(this.getHeight() > this.getWidth()){
                doorY = (this.getPosY()) / (this.getHeight() / 2);
            }
            else{
                doorY = (this.getPosY()) / (this.getHeight());
            }
            this.getScene().getMap().getTile(doorX, doorY).setType(MapTile.Type.WALL);
            if (this.getHeight() > this.getWidth()) {
                this.getScene().getMap().getTile(doorX, doorY + 1).setType(MapTile.Type.WALL);
            }
            else {
                this.getScene().getMap().getTile(doorX + 1, doorY).setType(MapTile.Type.WALL);
            }
            this.open = false;
            this.getScene().getMessageBus().publish(DOOR_CLOSED, this);
        }
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }
}
