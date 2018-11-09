package sk.tuke.kpi.oop.game.tools;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor>{
    private Animation animation = new Animation("hammer.png",16,16,10);

    public Hammer() {
       super(1);
       setAnimation(animation);
    }

    @Override
    public void useWith(Reactor actor) {
        if(actor == null){
            return;
        }
        if(actor instanceof Reactor){
            super.useWith(actor);
        }
    }
}
