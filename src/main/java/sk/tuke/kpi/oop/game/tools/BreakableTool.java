package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

abstract public class BreakableTool extends AbstractActor implements Usable {
    private int remainingUses;
    public BreakableTool(int remainingUses){

        this.remainingUses = remainingUses;
    }

    public int getRemainingUses(){
        return remainingUses;
    }

    public void setRemainingUses(int newUses){
        this.remainingUses=newUses;
    }

    @Override
    public void useWith(Object actor) {
    }

    public void use(){
        if(getRemainingUses()>0) {
            remainingUses--;
        }
        if (getRemainingUses() == 0) {
            getScene().removeActor(this);
        }
    }
}
