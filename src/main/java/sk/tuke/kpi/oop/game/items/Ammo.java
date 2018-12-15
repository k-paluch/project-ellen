package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo<T extends Armed> extends AbstractActor implements Usable<Ripley>{

    public Ammo() {
        super("ammo");
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

    @Override
    public void useWith(Ripley actor) {
        if ((this.getScene() != null) && (actor != null) && (actor.getAmmo() < 500)){
            actor.setAmmo(actor.getAmmo() + 50);
            this.getScene().removeActor(this);
        }
    }
}
