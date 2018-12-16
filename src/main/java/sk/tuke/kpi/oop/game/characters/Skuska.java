package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;

public class Skuska extends Alien {

    private Health health;
    private Behaviour<? super Alien> behaviour;
    public Skuska(int healthv){
        super(1000, new RandomlyMoving());
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        health = new Health(healthv);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }
}
