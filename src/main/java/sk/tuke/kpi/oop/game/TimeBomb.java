package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class TimeBomb extends AbstractActor {
    private boolean activated;
    private float cas;
    private Animation nonActiv =new Animation("sprites/bomb.png",16,16,0);
    private Animation Activ =new Animation("sprites/bomb_activated.png",16,16,0);
    private Animation expl =new Animation("sprites/small_explosion.png",16,16,0);

    public TimeBomb(float sec){
        setAnimation(nonActiv);
        activated = false;
        this.cas=sec;
    }
    public void activate(){
        activated = true;
        setAnimation(Activ);
        long sec = (long) cas;

        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reaction();

    }

    public boolean isActivated(){
        return activated;
    }

    public void reaction(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        Objects.requireNonNull(getScene()).removeActor(this);
    }



    public float getTime(){
        return cas;
    }

}
