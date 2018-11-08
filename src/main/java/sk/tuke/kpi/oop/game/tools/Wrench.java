package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool {
    private Animation animation = new Animation("sprites/wrench.png",16,16,10);
    public Wrench(DefectiveLight Deflight){
        super.setRemainingUses(2);
        setAnimation(animation);
    }

    @Override
    public void useWith(Object actor) {
        super.useWith(actor);
    }
}
