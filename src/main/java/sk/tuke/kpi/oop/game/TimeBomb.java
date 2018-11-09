package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
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
    public void addedToScene(Scene scene, Invoke invoke, Reactor defLight){
        new Loop<>(new Invoke(this::detonate)).scheduleOn(this);
    }

    private void detonate(){
        if(activated){
            setAnimation(Activ);
            cas--;
        }
        else if(cas==0){
            setAnimation(expl);
                getScene().removeActor(this);
        }
    }

}
