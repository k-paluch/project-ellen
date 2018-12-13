package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    private boolean activated;
    private float detonationTime;
    private Animation activatedAnimation;
    private Animation explodedAnimation;

    public TimeBomb(float detonationTime) {
        this.detonationTime = detonationTime;
        this.activated = false;
        this.activatedAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.1F, Animation.PlayMode.LOOP_PINGPONG);
        this.explodedAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.1F, Animation.PlayMode.ONCE);
        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }

    protected void onExplode(){}

    public boolean isActivated() {
        return activated;
    }

    public void activate(){
        this.activated = true;
        setAnimation(this.activatedAnimation);

        new ActionSequence<>(
            new Wait(this.detonationTime),
            new Invoke(this::explode)
        ).scheduleOn(this);


    }

    private void explode(){

        setAnimation(this.explodedAnimation);
        this.onExplode();

        new When<>(
            (action) -> this.getAnimation().getFrameCount() == this.getAnimation().getCurrentFrameIndex() + 1,
            new Invoke(() -> {
                this.getScene().removeActor(this);
            })
        ).scheduleOn(this);
    }
}
