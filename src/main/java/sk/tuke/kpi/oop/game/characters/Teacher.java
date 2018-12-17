package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Teacher  extends AbstractActor implements Movable, Alive, Enemy {

    public static final Topic<Teacher> TEACHER_TOPIC = Topic.create("teacher topic", Teacher.class);
    private Behaviour<? super Teacher> behaviour;
    private Health health;
    private boolean dead;

    public Teacher(Behaviour<Teacher> behaviour) {
        this.behaviour = behaviour;
        this.health = new Health(100);
        setAnimation(new Animation("sprites/teacher.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        dead=false;
        this.getAnimation().stop();
    }
    public Teacher(int healthv,Behaviour<? super Teacher> behaviour){
        setAnimation(new Animation("sprites/teacher.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        dead = false;
        this.behaviour = behaviour;
        health = new Health(healthv);
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new While<>(action -> true,
            new ActionSequence<>(
                new Invoke<>(this::AliveActor),
                new Wait<>(1)
            )).scheduleOn(this);
        getHealth().onExhaustion(()->{
            scene.getMessageBus().publish(TEACHER_TOPIC, this);
            scene.removeActor(this);
        });
        if (behaviour!=null){
            behaviour.setUp(this);
        }
    }

    public void AliveActor(){
        Scene scene = getScene();
        for(Actor actor : scene.getActors()){
            if(this.intersects(actor)&&(actor instanceof Alive) &&!(actor instanceof Enemy)){
                ((Alive) actor).getHealth().drain(20);
            }
        }
    }

    @Override
    public void removedFromScene(@NotNull Scene scene) {
        super.removedFromScene(scene);
    }

    public boolean isDead(){
        return dead;
    }

    @Override
    public int getSpeed() {
        return 1;
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
    public Health getHealth() {
        return this.health;
    }

}
