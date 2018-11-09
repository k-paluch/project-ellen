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
    private Animation nonActiv =new Animation("sprites/bomb.png",16,16,16);
    private Animation Activ =new Animation("sprites/bomb_activated.png",16,16,10);
    private Animation expl =new Animation("sprites/small_explosion.png",16,16,50);

    public TimeBomb(float sec){
        setAnimation(nonActiv);
        activated = false;
        this.cas=sec;
    }
    public void activate(){
        activated = true;
        setAnimation(Activ);
    }

    public boolean isActivated(){
        return activated;
    }

    public void reaction(){
        if(isActivated() && cas > 50){
            setAnimation(Activ);
            cas--;
        }
        else if(cas <= 50){
            setAnimation(expl);
            cas--;
            if(cas == 0)
                getScene().removeActor(this);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        new Loop<>(new Invoke(this::reaction)).scheduleOn(this);
        super.addedToScene(scene);
    }

    public float getTime(){
        return cas;
    }

}
