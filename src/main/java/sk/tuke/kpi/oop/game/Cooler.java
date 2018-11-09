package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends Reactor implements Switchable{
    private int zapnuty;
    private Reactor reactor;
    private Animation on = new Animation("sprites/fan.png", 32, 32, 0.2f,Animation.PlayMode.LOOP_PINGPONG);
    public Cooler(Reactor reactor){
        this.reactor = reactor;
        turnOn();
        setAnimation(on);
    }
    @Override
    public void turnOn(){
        zapnuty = 1;
        setAnimation(on);
    }
    @Override
    public void turnOff(){
        zapnuty = 0;
        on.pause();
    }
    @Override
    public boolean isOn(){
        if(zapnuty==1){
            return true;
        }
        else return false;
    }



    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }


    private void coolReactor(){
        if(isOn() && reactor!= null)
            reactor.decreaseTemperature(1);
    }

    public Reactor getCoolerReactor(){
        return reactor;
    }

}
