package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class AlienMother extends Alien {

    public AlienMother(Behaviour<Alien> randomlyMoving) {
        super(randomlyMoving);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }
}
