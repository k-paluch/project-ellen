package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {
    private Animation animation = new Animation("extinguisher.png", 16, 10);

    public FireExtinguisher() {
        super(1);
        setAnimation(animation);
    }

    @Override
    public void useWith(Reactor reactor) {
        if (reactor == null) {
            return;
        }
        if (getRemainingUses() > 0 && reactor.extinguish(this)) {
            setRemainingUses(getRemainingUses() - 1);
            if (getRemainingUses() <= 0) {
                this.getScene().removeActor(this);
            }
        }

    }

}
