package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool{
    private Animation animation = new Animation("hammer.png",16,16);

    public Hammer() {
        super.setRemainingUses(1);
       setAnimation(animation);
    }

    @Override
    public void useWith(Object actor) {
        super.useWith(actor);
    }
}
