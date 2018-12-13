package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private boolean isPowered;
    public Computer() {
        setAnimation(new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public int add(int num1, int num2){
        if (isPowered){
            return num1 + num2;
        }
        else {
            return 0;
        }
    }

    public float add(float num1, float num2){
        if (isPowered){
            return num1 + num2;
        } else {
            return 0;
        }
    }

    public int sub(int num1, int num2){
        if (isPowered){
            return num1 - num2;
        } else {
            return 0;
        }
    }

    public float sub(float num1, float num2){
        if (isPowered){
            return num1 - num2;
        } else {
            return 0;
        }
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
    }
}
