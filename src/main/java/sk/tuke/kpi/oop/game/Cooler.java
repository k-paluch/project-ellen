package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends Reactor implements Switchable{
    private int zapnuty;
    private Reactor reactor;
    private Animation on = new Animation("sprites/fan.png", 32, 32, 200);
    private Animation off = new Animation("sprites/fan.png", 32, 32, 0);
    public Cooler(Reactor reactor){
        if(reactor != null)
            this.reactor = reactor;
        zapnuty = 0;
        setAnimation(off);
    }
    @Override
    public void turnOn(){
        zapnuty = 1;
        setAnimation(on);
    }
    @Override
    public void turnOff(){
        zapnuty = 0;
        setAnimation(off);
    }
    @Override
    public boolean isOn(){
        if(zapnuty==1){
            return true;
        }
        else return false;
    }

    public void execute(float deltaTime) {

    }

    public void addedToScene(Scene scene, Invoke invoke, Reactor coolReactor){
        new Invoke(this::coolReactor).scheduleOn(this);
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }

    private void coolReactor(){
        super.decreaseTemperature(1);
        execute(1);
    }


}
