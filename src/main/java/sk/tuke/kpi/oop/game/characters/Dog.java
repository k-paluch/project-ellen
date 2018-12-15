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
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.items.Energy;

public class Dog extends AbstractActor implements Movable, Alive,Ally{

    private Behaviour<Dog> behaviour;
    private Health health;
    private Disposable drainLoop;
    private Behaviour<Dog> observing;

    public Dog(Behaviour<Dog> behaviour) {
        super("dogo");
        this.behaviour = behaviour;
        this.health = new Health(50);
        setAnimation(new Animation("sprites/dogo.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().stop();
    }

    public Dog(Observing<Dog ,Alien> observing) {
        super("dogo");
        this.observing = observing;
        this.health = new Health(100);
        setAnimation(new Animation("sprites/dogo.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().stop();
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (this.observing != null)
            this.observing.setUp(this);
        else if (this.behaviour != null)
            this.behaviour.setUp(this);

        this.getHealth().onExhaustion(() -> {
            scene.removeActor(this);
        });
        this.drainLoop = new Loop<>(
            new ActionSequence<>(
                new Wait<>(0),
                new Invoke<>(() ->{
                    for (Actor a : scene.getActors()){
                        if (a instanceof Alive && !(a instanceof Enemy) && a.intersects(this)){
                            ((Alive) a).getHealth().drain(1);
                        }
                    }
                })
            )
        ).scheduleOn(scene);
    }

    @Override
    public void removedFromScene(@NotNull Scene scene) {
        super.removedFromScene(scene);
        this.drainLoop.dispose();
    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().stop();
    }
}
