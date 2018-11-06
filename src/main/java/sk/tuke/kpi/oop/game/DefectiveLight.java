package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class DefectiveLight extends Light implements Repairable {
    private int random,zapnuty;
    private Animation light_off = new Animation("sprites/light_off.png", 16, 16, 10);
    private Animation light_on = new Animation("sprites/light_on.png", 16, 16, 10);
    public DefectiveLight(){
        super();
        random =(int)(Math.random() * 20);
    }

    public void addedToScene(Scene scene, Invoke invoke, Reactor defLight){
        random =(int)(Math.random() * 20);
        new Invoke(this::defLight).scheduleOn(this);
        new Loop<>(new Invoke(this::defLight)).scheduleOn(this);
    }


    private void defLight(){
        if(random ==1) {
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }
    @Override
    public void turnOn(){
        zapnuty = 1;
        setAnimation(light_on);
    }
    @Override
    public void turnOff(){
        zapnuty = 0;
        setAnimation(light_off);
    }
    @Override
    public boolean isOn(){
        if(zapnuty==1){
            return true;
        }
        else return false;
    }

    @Override
    public boolean repair() {
        getScene().dispose();
        return false;
    }
}
