package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import static java.lang.Math.abs;

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
        for (Actor actors : getScene().getActors()) {
            if ((actors instanceof ChainBomb) && (abs(actors.getPosX() - getPosX()) <= 50 && (abs(actors.getPosY() - getPosY()) <= 50))) {
                        ((ChainBomb) actors).activate();
                        chainreaction();
            }
        }
    }
}
