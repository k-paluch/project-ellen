package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {
    private boolean is_on, is_plugged;
    private Animation light_on = new Animation("sprites/light_on.png", 16, 16, 10);
    private Animation light_off = new Animation("sprites/light_off.png", 16, 16, 10);

    public Light() {

    }

    public void toogle() {
        if (is_on ? ((is_on = false)) : (is_on = true)) ;
        if(is_plugged&&is_on){
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }

    public void setElectricityFlow() {
        if (is_plugged ? (is_plugged = false) : (is_plugged = true)) ;
        if(is_plugged&&is_on){
            setAnimation(light_on);
        }
        else{
            setAnimation(light_off);
        }
    }
}
