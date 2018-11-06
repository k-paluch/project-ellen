package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Wrench extends BreakableTool {
    private Animation animation = new Animation("sprites/wrench.png",16,16,10);
    public Wrench(){
        super(2);
        setAnimation(animation);
    }
}
