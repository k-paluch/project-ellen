package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;

public class SmartCooler extends Cooler {
    private int zapnute;
    public SmartCooler (Reactor reactor){
        super(reactor);
        if(super.getTemperature()>2500){
            turnOn();
        }
        if(super.getTemperature()<1500){
            turnOff();
        }
    }

    @Override
    public void addedToScene(Scene scene, Invoke invoke, Reactor coolReactor) {
        if(super.getTemperature()>2500){
            turnOn();
        }
        if(super.getTemperature()<1500){
            turnOff();
        }
        super.addedToScene(scene, invoke, coolReactor);
    }

    @Override
    public void turnOn(){
        zapnute = 1;
    }
    @Override
    public void turnOff(){
        zapnute = 0;
    }
    @Override
    public boolean isOn(){
        if(zapnute==1){
            return true;
        }
        else{
            return false;
        }
    }
}
