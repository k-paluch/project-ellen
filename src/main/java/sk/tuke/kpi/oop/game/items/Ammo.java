package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

public class Ammo extends AbstractActor implements Usable<Armed>{

    public Ammo() {
        super("ammo");
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }



    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

    @Override
    public void useWith(Armed actor) {
        if(actor==null){
            return;
        }
        if(this.getScene() != null){
            actor.getFirearm().reload(50);
            this.getScene().removeActor(this);
        }
    }
}
