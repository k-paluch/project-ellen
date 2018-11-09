package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends Reactor implements Switchable, EnergyConsumer {
    private boolean is_set;
    private Animation light_on = new Animation("sprites/light_on.png", 16, 16, 10);
    private Animation light_off = new Animation("sprites/light_off.png", 16, 16, 10);
    private int turnedOn;

    public Light() {
        turnedOn = 0;
        turnOff();
    }

    public void toggle() {
        if (!isOn()) {
            turnOn();
        } else {
            turnOff();
        }
    }

    @Override
    public void turnOn() {
        turnedOn = 1;
        if (is_set) {
            this.setAnimation(light_on);
        }
    }

    @Override
    public void turnOff() {
        turnedOn = 0;
        this.setAnimation(light_off);
    }

    @Override
    public boolean isOn() {
        return turnedOn == 1;
    }

    @Override
    public void setPowered(boolean isPowered) {
        is_set = isPowered;
        if (is_set && isOn()) {
            setAnimation(light_on);
        } else {
            setAnimation(light_off);
        }
    }
}
