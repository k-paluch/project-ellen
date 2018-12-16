package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import java.util.Objects;

public class Move<T extends Movable> implements Action<T> {
    private boolean done;
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
    public boolean isDone() {
        return done;
    }

    @Override
    public void reset() {
        done = false;
        this.temp = true;
    }

    public void stop(){
        done = true;
        if (this.getActor() != null){
            this.getActor().stoppedMoving();
        }
    }


    @Override
    public void execute(float deltaTime) {
            if ((this.getActor() != null)){
                if (this.isDone()){
                    this.getActor().stoppedMoving();
                }
                if (this.temp) {
                    this.getActor().startedMoving(this.direction);
                    this.temp = false;
                }
                this.getActor().getAnimation().setRotation(this.direction.getAngle());
                int Xpozicia = this.getActor().getPosX();
                int Ypozicia = this.getActor().getPosY();
                this.getActor().setPosition(this.getActor().getPosX() + (this.direction.getDx() * this.getActor().getSpeed()), Ypozicia);
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())) {
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(Xpozicia, this.getActor().getPosY());
                }
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() + (this.direction.getDy() * this.getActor().getSpeed()));
                if (Objects.requireNonNull(this.getActor().getScene()).getMap().intersectsWithWall(this.getActor())) {
                    this.actor.collidedWithWall();
                    this.getActor().setPosition(this.actor.getPosX(), Ypozicia);
                }
                trvanie-=deltaTime;
                if(trvanie <= 1e-5 && !isDone()){
                    stop();
                }
        }
    }
}
