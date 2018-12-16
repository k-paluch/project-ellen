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

public class RandomlyMoving<T extends Movable> implements Behaviour<Movable> {

    private int[] uhol = new int[9];
    private Disposable disposable;

    public RandomlyMoving() {
        for (int i = 0, a = 0; a < 360; i++, a += 45){
            this.uhol[i] = a;
        }
    }

   @Override
    public void setUp(Movable actor) {
        if(actor==null){
            return;
        }
        if (actor.getScene() != null) {
            new Loop<>(
                new ActionSequence<>(
                    new Wait<>(1),
                    new Invoke<>(() -> {
                        if (this.disposable != null)
                            this.disposable.dispose();
                        this.disposable = new Move<>(Direction.fromAngle(this.uhol[Math.abs(new Random().nextInt(this.uhol.length))]), Float.MAX_VALUE).scheduleOn(actor);
                    })
                )
            ).scheduleOn(actor);
        }
    }
}
