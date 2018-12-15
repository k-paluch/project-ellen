package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import java.util.Objects;

public class Move<T extends Movable> extends AbstractAction<T> {

    private T actor;
    private Direction direction;
    private float trvanie;
    private boolean temp;

    public Move(Direction direction, float trvanie) {
        this.direction = direction;
        this.trvanie = trvanie;
        this.temp = true;
    }

    @Nullable
    @Override
    public T getActor() {
        return this.actor;
    }

    @Override
    public void setActor(T t) {
        this.actor = t;
    }

    @Override
    public void reset() {
        this.setDone(false);
    }

    public void stop(){
        if (this.getActor() != null){
            this.getActor().stoppedMoving();
        }
        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull Scene scene) {
        return super.scheduleOn(scene);
    }

    @Override
    public void execute(float deltaTime) {
        if ((this.getActor() != null)){
            if (this.isDone()){
                this.getActor().stoppedMoving();
            }
            else {
                if (this.temp) {
                    this.getActor().startedMoving(this.direction);
                    this.getActor().getAnimation().setRotation(this.direction.getAngle());
                }
                int Xpozicia = this.getActor().getPosX();
                int Ypozicia = this.getActor().getPosY();
                this.getActor().setPosition(this.getActor().getPosX() + (this.direction.getDx() * this.getActor().getSpeed()), Ypozicia);
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())){
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(Xpozicia, this.getActor().getPosY());
                }
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() + (this.direction.getDy() * this.getActor().getSpeed()));
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())){
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(this.actor.getPosX(), Ypozicia);
                }
                double math = Math.floor(Math.abs(this.trvanie -= deltaTime));
                if ( math == 0) {
                    this.stop();
                }
            }
        }
        setDone(true);
    }
}
