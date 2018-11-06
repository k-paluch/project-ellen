<<<<<<< HEAD
package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {
    private Animation animation = new Animation("extinguisher.png",16,16);
    public FireExtinguisher(){
        super(1);
        setAnimation(animation);
    }

}
=======
package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class FireExtinguisher extends AbstractActor {
    
}
>>>>>>> 653695e3498e9624882463b4c8919ee60c878e78
