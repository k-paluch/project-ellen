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

    public double add(double first, double second){
        return first+second;
    }
    public int add(int first, int second){
        return first+second;
    }
    public double sub(double first, double second){
        return first-second;
    }
    public int sub(int first, int second){
        return first-second;
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
    public void setElectricityFlow(boolean isPowered) {
        if(isPowered){
            setAnimation(computer_on);
        }
        else{
            setAnimation(computer_off);
        }
    }
}