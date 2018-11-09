package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class PowerSwitch extends AbstractActor {
    private Switchable tooglerer;

    public PowerSwitch(Switchable toogler) {
        tooglerer = toogler;
        setAnimation(new Animation("resources/images/switch.png", 16, 16, 100));
    }

    public void switchOff() {
        if (tooglerer != null)
            tooglerer.turnOff();
    }

    public void switchOn() {
        if (tooglerer != null)
            tooglerer.turnOn();
    }

    public Switchable getDevice() {
        return tooglerer;
    }

}
