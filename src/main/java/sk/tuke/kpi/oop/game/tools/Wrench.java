package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight> {
    private Animation animation = new Animation("sprites/wrench.png",16,16,10);
    public Wrench(){
        super(2);
        setAnimation(animation);
    }

    @Override
    public void useWith(DefectiveLight actor) {
        if(actor==null){
            return;
        }
        if(actor instanceof DefectiveLight){
            super.useWith(actor);
        }
    }
}
