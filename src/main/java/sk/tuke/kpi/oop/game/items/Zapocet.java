package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class Zapocet extends AbstractActor implements Collectible, Usable<LockedDoor> {

    public Zapocet() {
        super("zapocet");
        setAnimation(new Animation("sprites/zapocet.png", 16, 16));
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }

    @Override
    public void useWith(LockedDoor door) {
        if (door.getScene() != null){
            door.unlock();
            door.open();
            door.getScene().removeActor(this);
        }
    }
}
