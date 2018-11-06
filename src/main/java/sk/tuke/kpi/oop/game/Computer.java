package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private Animation computer_animation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation computer = new Animation("sprites/computer.png", 80, 48, 0.2f);
    public Computer(){
        setAnimation(computer);
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
    public void setElectricityFlow(boolean isPowered) {
        if(isPowered){
            setAnimation(computer_animation);
        }
        else{
            setAnimation(computer);
        }
    }
}