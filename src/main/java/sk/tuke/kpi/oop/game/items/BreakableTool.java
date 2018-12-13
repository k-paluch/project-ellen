package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.Repairable;

public abstract class BreakableTool<T extends Repairable> extends AbstractActor implements Usable<T>, Collectible {

    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    @Override
    public void useWith(T actor) {
        this.remainingUses -=1;
        if ((this.remainingUses <= 0) && (this.getScene() != null)){
            this.getScene().removeActor(this);
        }
    }
}
