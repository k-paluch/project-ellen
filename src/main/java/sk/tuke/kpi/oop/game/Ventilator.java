package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("VENTILATOR_REPAIRED", Ventilator.class);

    public Ventilator() {
        super("ventilator");
        setAnimation(new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP));
        this.getAnimation().stop();
        this.getAnimation().resetToFirstFrame();
    }

    @Override
    public boolean repair() {
        if (this.getScene() != null){
            this.getAnimation().play();
            this.getScene().getMessageBus().publish(Ventilator.VENTILATOR_REPAIRED, this);
            return true;
        }
        return false;
    }
}
