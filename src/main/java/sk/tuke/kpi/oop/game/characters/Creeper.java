package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Creeper extends AbstractActor implements Movable, Alive, Enemy {

    private Disposable disposable;



    private Behaviour<? super Creeper> behaviour;
    private Health health;
    public Creeper(Behaviour<Creeper> behaviour) {
        this.behaviour = behaviour;
        this.health = new Health(100);
        setAnimation(new Animation("sprites/11401.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().stop();
    }
    public Creeper(int healthv, Behaviour<? super Creeper> behaviour){
        setAnimation(new Animation("sprites/11401.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.behaviour = behaviour;
        health = new Health(healthv);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        this.disposable = new Loop<>(
            new ActionSequence<>(
                new Wait<>(0),
                new Invoke<>(() -> {
                    for (Actor a : scene.getActors()) {
                        if (a instanceof Alive && !(a instanceof Enemy) && a.intersects(this)) {
                            ((Alive) a).getHealth().drain(100);
                            setAnimation(new Animation("sprites/explotion.png", 16, 16, 0.1F,Animation.PlayMode.LOOP_PINGPONG));
                            new Wait<>(3);
                            removedFromScene(scene);
                            this.disposable.dispose();
                        }
                    }
                })
            )
        ).scheduleOn(scene);
    }

    @Override
    public void removedFromScene(@NotNull Scene scene) {
        super.removedFromScene(scene);
        this.disposable.dispose();
    }

}
