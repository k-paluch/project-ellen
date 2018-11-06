package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class BreakableTool extends AbstractActor {
    private int remairingUses;

    public BreakableTool(int remairingUses){
        this.remairingUses = remairingUses;
    }

    public int getRemairingUses(){
        return remairingUses;
    }
}
