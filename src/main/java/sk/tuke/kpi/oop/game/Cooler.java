package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private Reactor reactor;
    private boolean isOn;
    private Animation onAnimation;
    private Animation offAnimation;

    public Cooler(Reactor reactor) {

        this.isOn = false;
        if (reactor != null){
            this.reactor = reactor;
        }

        this.onAnimation = new Animation("sprites/fan.png", 32, 32, 0.2F, Animation.PlayMode.LOOP_PINGPONG
        );

        this.offAnimation = new Animation("sprites/fan.png", 32, 32, 0, Animation.PlayMode.ONCE_REVERSED
        );

        setAnimation(this.offAnimation);
    }

    public Reactor getReactor() {
        return reactor;
    }

    public void turnOn(){
        this.isOn = true;
        setAnimation(this.onAnimation);
    }

    public void turnOff(){
        this.isOn = false;
        setAnimation(this.offAnimation);
    }

    public boolean isOn() {
        return isOn;
    }

    public void coolReactor(){
        if ((this.isOn()) && (this.reactor != null)){
            this.reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }
}
