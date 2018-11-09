package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer,Switchable {
    private int zapnute;
    private Animation computer_on = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation computer_off = new Animation("sprites/computer.png", 80, 48, 0.2f);
    public Computer(){
        setAnimation(computer_off);
    }

    public float add(float first, float second){
        if(isOn()) {
            return first + second;
        }
        else {
            return 0;
        }
    }
    public int add(int first, int second){
        if(isOn()) {
            return first + second;
        }
        else {
            return 0;
        }
    }
    public float sub(float first, float second){
        if(isOn()) {
            return first + second;
        }
        else {
            return 0;
        }
    }
    public int sub(int first, int second){
        if(isOn()) {
            return first + second;
        }
        else {
            return 0;
        }
    }

    @Override
    public void turnOn() {
        zapnute = 1;
    }

    @Override
    public void turnOff() {
        zapnute =0;
    }

    @Override
    public boolean isOn() {
        if(zapnute ==1){
            return true;
        }
        else
        return false;
    }

    @Override
    public void setPowered(boolean isPowered) {
        if(isPowered){
            setAnimation(computer_on);
        }
        else{
            setAnimation(computer_off);
        }
    }


}
