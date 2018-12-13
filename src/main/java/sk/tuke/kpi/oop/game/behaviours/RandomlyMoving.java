package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Random;

public class RandomlyMoving<T extends Movable> implements Behaviour<T> {

    private int[] directionAngles = new int[9];
    private Disposable move;

    public RandomlyMoving() {
        for (int i = 0, a = 0; a < 360; i++, a += 45){
            this.directionAngles[i] = a;
        }
    }

   @Override
    public void setUp(T actor) {

        if (actor.getScene() != null) {
            new Loop<T>(
                new ActionSequence<T>(
                    new Wait<T>(1),
                    new Invoke<T>(() -> {
                        if (this.move != null)
                            this.move.dispose();
                        this.move = new Move<T>(
                            Direction.fromAngle(this.directionAngles[Math.abs(new Random().nextInt(this.directionAngles.length))]),
                            Float.MAX_VALUE
                        ).scheduleOn(actor);
                    })
                )
            ).scheduleOn(actor);
        }
    }
}
