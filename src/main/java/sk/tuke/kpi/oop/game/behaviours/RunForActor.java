package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.Random;

public class RunForActor<T extends Movable> implements Behaviour<T> {


    private T runner;
    private Disposable move;
    public RunForActor() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setUp(T actor) {
        if ((actor.getScene() != null)){
            {
                this.runner = (T) actor.getScene().getFirstActorByName("Ellen");
                this.runner = (T) actor.getScene().getFirstActorByName("You");
            }
            if (this.runner == null) {
                return;
            }
            Random r = new Random();
            new Loop<>(
                new ActionSequence<>(
                    new Wait<>(0),
                    new Invoke<>(() -> {
                        if (r.nextInt() % 2 == 0){
                            return;
                        }
                        if (r.nextInt() % 2 == 0){
                            return;
                        }
                        int x = 0, y = 0;
                        if (actor.getScene().getMap().intersectsWithWall(this.runner)){
                            for (Actor a : actor.getScene().getActors()){
                                if ((a instanceof Door) && (a.intersects(this.runner))){
                                    x = a.getPosX();
                                    y = a.getPosY();
                                    break;
                                }
                            }
                        }
                        if (!(x > 0) && !(y > 0)) {
                            x = actor.getPosX();
                            y = actor.getPosY();
                        }
                        int newX, newY;
                        if (x < this.runner.getPosX()) {
                            newX = 1;
                        } else if (x == this.runner.getPosX()) {
                            newX = 0;
                        } else {
                            newX = -1;
                        }
                        if (y < this.runner.getPosY()) {
                            newY = 1;
                        } else if (y == this.runner.getPosY()) {
                            newY = 0;
                        } else {
                            newY = -1;
                        }
                        if (this.move != null)
                            this.move.dispose();
                        for (Direction direction : Direction.values()){
                            if ((direction.getDx() == newX) && (direction.getDy() == newY)){
                                System.out.println(direction);
                                this.move = new Move<>(direction, Float.MAX_VALUE).scheduleOn(actor);
                            }
                        }
                    })
                )
            ).scheduleOn(actor);
        }
    }
}
