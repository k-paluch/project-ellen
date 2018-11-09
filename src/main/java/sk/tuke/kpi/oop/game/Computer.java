package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer, Switchable {
    private boolean zapnute;
    private Animation computer_on = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation computer_off = new Animation("sprites/computer.png", 80, 48, 0.2f);

    public Computer() {
        setAnimation(computer_off);
        turnOff();
    }

    public float add(float first, float second) {
        if(isOn()){
            return first + second;
        }
        else{
            return 0;
        }

    }

    public int add(int first, int second) {
        if(isOn()){
            return first + second;
        }
        else{
            return 0;
        }

    }

    public float sub(float first, float second) {
        if(isOn()){
            return first - second;
        }
        else{
            return 0;
        }

    }

    public int sub(int first, int second) {
        if(isOn()){
            return first - second;
        }
        else{
            return 0;
        }

    }

    @Override
    public void turnOn() {
        zapnute = true;
        setAnimation(computer_on);
    }

    @Override
    public void turnOff() {
        zapnute = false;
        setAnimation(computer_off);
    }

    @Override
    public boolean isOn() {
        return zapnute;
    }

    @Override
    public void setPowered(boolean isPowered) {
        zapnute=isPowered;
        if (isPowered) {
            setAnimation(computer_on);
        } else {
            setAnimation(computer_off);
        }
    }
}
