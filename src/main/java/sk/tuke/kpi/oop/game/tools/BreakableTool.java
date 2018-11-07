package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

abstract public class BreakableTool extends AbstractActor implements Usable {
    private int remainingUses;
    public BreakableTool(1){
        this.remainingUses = 0;
    }

    public int getRemainingUses(){
        return remainingUses;
    }

    public void setRemainingUses(int newUses){
        this.remainingUses=newUses;
    }

    @Override
    public void useWith(Object actor) {
        use();
    }

    public void use(){
        remainingUses --;
        if(remainingUses==0) {
            getScene().removeActor(this);
        }
    }
}
