package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer {

    private boolean isTurnedOn;
    private boolean hasElectricityFlow;
    private Animation onAnimation;
    private Animation offAnimation;

    public Light() {
        this.hasElectricityFlow = false;
        this.isTurnedOn = false;
        this.onAnimation = new Animation("sprites/light_on.png", 16, 16);
        this.offAnimation = new Animation("sprites/light_off.png", 16, 16);
        setAnimation(this.offAnimation);
    }

    private void updateAnimation(){
        if (this.isTurnedOn && this.hasElectricityFlow){
            setAnimation(this.onAnimation);
        } else {
            setAnimation(this.offAnimation);
        }
    }

    public void toggle(){
        if (this.isOn()){
            this.turnOff();
        } else {
            this.turnOn();
        }
    }

    public void setElectricityFlow(boolean hasFlow){
        this.hasElectricityFlow = hasFlow;
        this.updateAnimation();
    }

    @Override
    public void turnOn() {
        this.isTurnedOn = true;
        this.updateAnimation();
    }

    @Override
    public void turnOff() {
        this.isTurnedOn = false;
        this.updateAnimation();
    }

    @Override
    public boolean isOn() {
        return this.isTurnedOn;
    }

    @Override
    public void setPowered(boolean isPowered) {

        this.setElectricityFlow(isPowered);
    }
}
