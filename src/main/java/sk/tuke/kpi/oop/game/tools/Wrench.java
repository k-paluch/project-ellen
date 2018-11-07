<<<<<<< HEAD
package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool {
    private Animation animation = new Animation("sprites/wrench.png",16,16,10);
    public Wrench(DefectiveLight Deflight){
        super.setRemainingUses(2);
        setAnimation(animation);
    }
}
=======
package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Wrench extends BreakableTool {
    private Animation animation = new Animation("sprites/wrench.png",16,16,10);
    public Wrench(){
        super(2);
        setAnimation(animation);
    }
}
>>>>>>> 6f5926bdf82b823baf3ed91eb3045c7c14175782
