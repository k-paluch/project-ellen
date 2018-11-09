package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {
    private Animation animation = new Animation("extinguisher.png",16,16);
    public FireExtinguisher(){
        super(1);
        setAnimation(animation);
    }

    @Override
    public void useWith(Object actor) {
        if(actor==null){
            return;
        }
        use();
    }
}
