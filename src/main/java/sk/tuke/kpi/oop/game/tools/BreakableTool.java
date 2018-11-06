package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

abstract public class BreakableTool extends AbstractActor implements Usable {
    private int remairingUses;

    public BreakableTool(int remairingUses){
        this.remairingUses = remairingUses;
    }

    public int getRemairingUses(){
        return remairingUses;
    }

    public void setRemairingUses(int newUses){
        this.remairingUses=newUses;
    }

    @Override
    public void useWith(Actor actor) {
        use();
    }

    public void use(){
        remairingUses --;
        if(remairingUses==0) {
            getScene().removeActor(this);
        }
    }
}
