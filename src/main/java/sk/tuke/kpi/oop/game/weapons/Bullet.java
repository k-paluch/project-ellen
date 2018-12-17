package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Bullet extends AbstractActor implements Fireable, Movable {

    public Bullet() {
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
    }

    protected void intersectsWithEnemy(Scene scene){
        new Loop<>(
            new Invoke<>(() -> {
                for (Actor actor : scene.getActors()) {
                    if ((this.intersects(actor)) /*&& (actor instanceof Enemy)*/ && (actor instanceof Alien)) {
                        ((Alive) actor).getHealth().drain(20);
                        this.collidedWithWall();
                    }
                }
            })
        ).scheduleOn(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.intersectsWithEnemy(scene);
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().stop();
    }

    @Override
    public void collidedWithWall() {
        if (this.getScene() != null){
            this.getScene().removeActor(this);
        }
    }
}
