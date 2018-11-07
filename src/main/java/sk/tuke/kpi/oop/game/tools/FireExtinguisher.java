<<<<<<< HEAD
package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {
    private Animation animation = new Animation("extinguisher.png",16,16);
    public FireExtinguisher(){
        super.setRemainingUses(1);
        setAnimation(animation);
    }
}
=======

package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {
    private Animation animation = new Animation("extinguisher.png",16,16);
    public FireExtinguisher(){
        super(1);
        setAnimation(animation);
    }

}
>>>>>>> 6f5926bdf82b823baf3ed91eb3045c7c14175782
