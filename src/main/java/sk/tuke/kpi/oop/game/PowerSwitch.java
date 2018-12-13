package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor implements Switchable {

    private Switchable switchable;

    public PowerSwitch(Switchable switchable) {
        this.switchable = switchable;

         setAnimation(new Animation(
            "sprites/switch.png",
            16,
            16
        ));
    }

    public void toggle(){
        if (this.isOn()){
            this.turnOff();
        } else {
            this.turnOn();
        }
    }

    public void switchOn() {
       if (this.getDevice() != null) {
           this.getAnimation().setTint(Color.WHITE);
           this.switchable.turnOn();
       }
    }

    public void switchOff() {
        if (this.getDevice() != null){
            this.getAnimation().setTint(Color.GRAY);
            this.switchable.turnOff();
        }
    }

    public Switchable getDevice(){
        return this.switchable;
    }

    @Override
    public void turnOn() {
        this.switchOn();
    }

    @Override
    public void turnOff() {
        this.switchOff();
    }

    @Override
    public boolean isOn() {
        return this.switchable.isOn();
    }
}
