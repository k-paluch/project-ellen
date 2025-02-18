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
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {


    private Health health;
    private Behaviour<? super Alien> behaviour;

    public Alien() {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        health = new Health(100);
    }

    public Alien(int healthv,Behaviour<? super Alien> behaviour){
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        this.behaviour = behaviour;
        health = new Health(healthv);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new While<>(action -> true,
            new ActionSequence<>(
                new Invoke<>(this::aliveActor),
                new Wait<>(0)
            )).scheduleOn(this);
        getHealth().onExhaustion(()->
            scene.removeActor(this));
        if (behaviour!=null){
            behaviour.setUp(this);
        }
    }

    public void aliveActor(){
        Scene scene = getScene();
        for(Actor actor : scene.getActors()){
            if(this.intersects(actor)&&(actor instanceof Alive) &&!(actor instanceof Enemy)){
                ((Alive) actor).getHealth().drain(1);
            }
        }
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
