package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;

public class AlienMother extends Alien {

    private Health health;
    private Behaviour<? super Alien> behaviour;
    public AlienMother(int healthv){
        super(1000, new RandomlyMoving());
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1F, Animation.PlayMode.LOOP_PINGPONG));
        health = new Health(healthv);
    }
}
