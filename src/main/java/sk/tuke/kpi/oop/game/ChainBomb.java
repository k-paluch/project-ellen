package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ChainBomb extends TimeBomb {

    public ChainBomb(float sec) {
        super(sec);

    }

    public void addedToScene(Scene scene, Invoke invoke, Reactor defLight){
        new Loop<>(new Invoke(this::detonate)).scheduleOn(this);
    }

    private void detonate(){
        if(getTime()==50)
            for (Actor actor : getScene()) {
                if (new Ellipse2D.Float(getPosX(), getPosY(), 50,50).intersects(
                    new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getAnimation().getWidth(), actor.getAnimation().getHeight())))
                    if (actor instanceof ChainBomb) {
                        ((ChainBomb) actor).activate();
                    }
            }
    }
}
