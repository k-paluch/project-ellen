package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends Reactor implements Switchable {
    private int zapnuty;
    private Reactor reactor;
    private Animation on = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        turnOn();
        setAnimation(on);
    }

    @Override
    public void turnOn() {
        zapnuty = 1;
        on.play();
    }

    @Override
    public void turnOff() {
        zapnuty = 0;
        on.pause();
    }

    @Override
    public boolean isOn() {
        return zapnuty == 1;
    }


    @Override
    public void addedToScene(Scene scene) {
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
        super.addedToScene(scene);
    }


    private void coolReactor() {
        if (isOn() && reactor != null)
            reactor.decreaseTemperature(1);
    }

    public Reactor getCoolerReactor() {
        return reactor;
    }
}
