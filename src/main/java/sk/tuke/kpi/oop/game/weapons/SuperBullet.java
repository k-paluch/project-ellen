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

public class SuperBullet extends AbstractActor implements Fireable, Movable {
    public SuperBullet() {
        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }

    @Override
    public int getSpeed() {
        return 9;
    }

    protected void intersectsWithEnemy(Scene scene){
        new Loop<>(
            new Invoke<>(() -> {
                for (Actor actor : scene.getActors()) {
                    if ((this.intersects(actor)) /*&& (actor instanceof Enemy)*/ && (actor instanceof Alive)) {
                        ((Alive) actor).getHealth().drain(100);
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
