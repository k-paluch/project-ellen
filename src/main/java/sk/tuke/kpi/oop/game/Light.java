package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends Reactor implements Switchable, EnergyConsumer {
    private boolean is_set;
    private Animation light_on = new Animation("sprites/light_on.png", 16, 16, 10);
    private Animation light_off = new Animation("sprites/light_off.png", 16, 16, 10);
    private int turnedOn;
    public Light() {
        turnOff();
    }

    public void toogle() {
        if (turnedOn==0){
            turnOn();
        }
        else{
            turnOff();
        }
    }
    @Override
    public void turnOn(){
        turnedOn = 1;
        if(is_set){
            this.setAnimation(light_on);
        }
    }
    @Override
    public void turnOff(){
        turnedOn =0;
        this.setAnimation(light_off);
    }
    @Override
    public boolean isOn(){
        if (turnedOn==1){
            return true;
        }
        else{
            return false;
        }
    }

    /*public void setElectricityFlow(boolean is_plugged) {
        is_set = is_plugged;
        if(is_set&&isOn()){
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }*/

    @Override
    public void setElectricityFlow(boolean isPowered) {
        is_set = isPowered;
        if(is_set&&isOn()){
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }
}
