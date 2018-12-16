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
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.items.Zapocet;

public class Teacher  extends AbstractActor implements Movable, Alive, Enemy {

    public static final Topic<Teacher> TEACHER_TOPIC = Topic.create("teacher died", Teacher.class);
    private Behaviour<Teacher> behaviour;
    private Health health;
    private Disposable drainLoop;
    private Behaviour<Teacher> observing;

    public Teacher(Behaviour<Teacher> behaviour) {
        this.behaviour = behaviour;
        this.health = new Health(100);
        setAnimation(new Animation("sprites/teacher.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().stop();
    }

    public Teacher(Observing<Teacher ,?> observing) {

        this.observing = observing;
        this.health = new Health(100);
        setAnimation(new Animation("sprites/teacher.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().stop();
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    public void die(){
        this.setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
        this.getAnimation().resetToFirstFrame();
        this.getAnimation().play();
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
            new Zapocet();
        });
        this.drainLoop = new Loop<>(
            new ActionSequence<>(
                new Wait<>(0),
                new Invoke<>(() ->{
                    for (Actor aktor : scene.getActors()){
                        if (aktor instanceof Alive && !(aktor instanceof Enemy) && aktor.intersects(this)){
                            ((Alive) aktor).getHealth().drain(0);
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
