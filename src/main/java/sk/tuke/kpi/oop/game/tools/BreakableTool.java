package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

abstract public class BreakableTool<Actor> extends AbstractActor implements Usable<Actor> {
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
    public void useWith(Actor actor){
        if(actor==null) {
            return;
        }
        if(getRemainingUses()>0){
            this.remainingUses --;
            if(getRemainingUses() == 0){
                this.getScene().removeActor(this);
            }
        }

        if(getRemainingUses() == 0) {
            getScene().removeActor(this);
        }
    }

}
