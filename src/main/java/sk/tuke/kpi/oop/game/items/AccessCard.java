package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {


    public AccessCard() {
        super("access card");
        setAnimation(new Animation("sprites/key.png", 16, 16));
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }

    @Override
    public void useWith(LockedDoor actor) {
        if ((actor.getScene() != null) && (actor.isLocked())){
            actor.unlock();
            actor.getScene().removeActor(this);
        }
    }
}
