package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.SuperFirearm;

public class Ammo extends AbstractActor implements Usable<Ripley>, Armed {

    private Firearm firearm;
    private SuperFirearm superFirearm;

    public Ammo() {
        super("ammo");
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }

    @Override
    public void setFirearm(Firearm firearm) {
        this.firearm = firearm;
    }

    @Override
    public void setSuperFirearm(SuperFirearm superFirearm) {
        this.superFirearm = superFirearm;
    }


    @Override
    public Firearm getFirearm() {
        return firearm;
    }


    @Override
    public SuperFirearm getSuperFirearm() {
        return superFirearm;
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }

    @Override
    public void useWith(Ripley actor) {
        if ((this.getScene() != null) && (actor != null) && (actor.getAmmo() < 500)){
            actor.setAmmo(actor.getAmmo() + 50);
            this.getScene().removeActor(this);
        }
    }
}
