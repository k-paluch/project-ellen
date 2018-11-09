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

    @Override
    public void addedToScene(Scene scene) {
        new Loop<>(new Invoke(this::chainreaction)).scheduleOn(this);
        super.addedToScene(scene);
    }

    private void chainreaction() {
        super.reaction();
        for (Actor actor : getScene().getActors()) {
            if (actor instanceof ChainBomb && new Ellipse2D.Float(this.getPosX(), this.getPosY(), 50, 50).intersects(
                new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getAnimation().getWidth(), actor.getAnimation().getHeight()))) {
                ((ChainBomb) actor).activate();

            }
        }
    }
}
