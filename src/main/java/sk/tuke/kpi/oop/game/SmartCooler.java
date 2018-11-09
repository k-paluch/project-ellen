package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {
    public SmartCooler(Reactor reactor) {
        super(reactor);
    }

    @Override
    public void addedToScene(Scene scene) {
        new Loop<>(new Invoke(this::smart)).scheduleOn(this);
        super.addedToScene(scene);
    }

    public void smart() {
        if (
            getCoolerReactor() != null) {
            if (getCoolerReactor().getTemperature() < 1500)
                turnOff();
            if (getCoolerReactor().getTemperature() > 2500)
                turnOn();
        }
    }
}
