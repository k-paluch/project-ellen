package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class SuperAmmo extends AbstractActor implements Usable<Ripley> {
    public SuperAmmo() {
        super("SuperAmmo");
        setAnimation(new Animation("sprites/bombz.png", 16, 16));
    }
    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }

    @Override
    public void useWith(Ripley actor) {
        if ((this.getScene() != null) && (actor != null) && (actor.getSuperAmmo() < 1)){
            actor.setSuperAmmo(actor.getSuperAmmo() + 1);
            this.getScene().removeActor(this);
        }
    }
}
