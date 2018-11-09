package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;


public class TimeBomb extends AbstractActor {
    private boolean activated;
    private float cas;
    private Animation nonActiv = new Animation("sprites/bomb.png", 16, 16, 16);
    private Animation Activ = new Animation("sprites/bomb_activated.png", 16, 16, 10);
    private Animation expl = new Animation("sprites/small_explosion.png", 16, 16, 50);

    public TimeBomb(float sec) {
        setAnimation(nonActiv);
        activated = false;
        this.cas = sec;
    }

    public void activate() {
        activated = true;
        setAnimation(Activ);
        timeOut(cas);
    }

    public boolean isActivated() {
        return activated;
    }

    public void reaction() {
        setAnimation(expl);

        Objects.requireNonNull(getScene()).removeActor(this);

    }


    private void timeOut(float seconds) {

        long sec = (long) seconds;

        long startTime = System.currentTimeMillis();
        for (int count = 0; ;count++) {
            long now = System.currentTimeMillis();
            if(now - startTime >= sec*1000) break;
        }
        reaction();
    }


    public float getTime() {
        return cas;
    }

}
