package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.SuperArmed;

public class SuperAmmo extends AbstractActor implements Usable<SuperArmed>{

    public SuperAmmo() {
        super("superammo");
        setAnimation(new Animation("sprites/bombz.png", 16, 16));
    }


    @Override
    public Class<SuperArmed> getUsingActorClass() {
        return SuperArmed.class;
    }

    @Override
    public void useWith(SuperArmed actor) {
        if(actor==null){
            return;
        }
        if(this.getScene()!=null && (actor !=null)&(actor.getSuperFirearm().getSuperAmmo()<1)){
            actor.getSuperFirearm().reload(1);
            this.getScene().removeActor(this);
        }
    }

}
