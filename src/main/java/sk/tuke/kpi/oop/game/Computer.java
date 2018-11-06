package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {
    private Animation computer_animation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    public Computer(){
        setAnimation(computer_animation);
    }
    public int add(int input){
        return  input;
    }
    public int sub(int input){
        return  input;
    }

    public float add(float input){
        return  input;
    }

    public float sub(float input){
        return  input;
    }
}
