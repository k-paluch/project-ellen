package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.tools.Hammer;


import static java.lang.Math.max;

public class Reactor extends AbstractActor {
    private int temperature, damage;
    private boolean is_on;
    private Animation normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
    public Reactor() {
        this.temperature = 0;
        this.damage = 0;
        turnOff();
    }

    public void setTemperature(int newTemp) {

        this.damage = newTemp;
    }

    public void setDamage(int newDamage) {

        this.damage = newDamage;
    }
    void updateAnimation(){
        if(!is_on) {
            if (damage == 100) {
                setAnimation(brokenAnimation);
            }
            else{
                setAnimation(offAnimation);
            }
            return;
        }
        else{
            if (damage == 100) {
                setAnimation(brokenAnimation);
            }
            else if(temperature>4000){
                setAnimation(hotAnimation);
            }
            else{
                setAnimation(normalAnimation);
            }
        }
    }
    public void increaseTemperature(double increment) {
        if (increment < 0) {
            return;
        }
        this.temperature += increment;
        if (this.damage > 33 && this.damage < 66) {
            increment *= 1.5;
        } else if (this.damage > 66) {
            increment *= 2;
        }

        temperature = max(6000,(int)increment+temperature);
        this.damage = max(100, ((this.temperature - 2000) / 4000) * 100);
        updateAnimation();
    }

    public void decreaseTemperature(double decrement) {
        if(decrement < 0) {
            return;
        }
        if (damage > 50) {
            decrement /= 2;
        }
        if (damage == 100) {
            decrement = 0;
        }
        this.temperature = max(0,temperature-(int)decrement);
        updateAnimation();
    }

    public void repairWith(Hammer hammer) {
        final int max_damage;
        final int min_damage;
        max_damage = 99;
        min_damage = 0;
        if (hammer == null) return;

        if (damage >= min_damage && damage <= max_damage) {
            hammer.use();
            damage = max(0, damage - 50);
        }

    }

    public int getTemperature() {

        return temperature;
    }

    public int getDamage() {

        return damage;
    }

    public void turnOn(){
        is_on =true;
    }

    public void turnOff() {
        is_on =false;
    }

    public boolean isRunning(){
        return (is_on?true:false);
    }

    public void addLight(){
    }

    public void removeLight(){

    }

    void extinguished() {
        Animation extingushedAnimaton = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        if (getAnimation() == brokenAnimation) {
            setAnimation(extingushedAnimaton);
        }
    }
}