package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> {

    private Animation normalAnimation;

    public Hammer(){
        super(1);
        this.normalAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(this.normalAnimation);
    }

    public Hammer(int remainingUses){
        super(remainingUses);
        this.normalAnimation = new Animation("sprites/hammer.png", 16, 16);
        setAnimation(this.normalAnimation);
    }

    @Override
    public void useWith(Repairable repairable) {
        if ((repairable != null) && (repairable.repair())){
            super.useWith(repairable);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
