package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class Zapocet extends AbstractActor implements Collectible {

    public Zapocet() {
        super("zapocet");
        setAnimation(new Animation("sprites/zapocet.png", 16, 16));
    }
}
