package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private boolean activated;
    private float cas;
    private Animation nonActiv =new Animation("sprites/bomb.png",16,16,0);
    private Animation Activ =new Animation("sprites/bomb_activated.png",16,16,0);
    private Animation expl =new Animation("sprites/small_explosion.png",16,16,0);

    public TimeBomb(float sec){
        setAnimation(nonActiv);
        activated = false;
        cas = sec;
    }
    public void activate(){
        activated = true;
        setAnimation(Activ);
    }

    public boolean isActivated(){
        if(activated){
            return true;
        }
        else return  false;
    }
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::detonate)).scheduleOn(this);
    }

    private void detonate(){
        if(activated&& getTime()>50){
            setAnimation(Activ);
            cas--;
        }
        else if(cas<=50){
            setAnimation(expl);
            cas--;
            if(cas==0){
                getScene().removeActor(this);
            }
        }
    }

    public float getTime(){
        return cas;
    }

}
