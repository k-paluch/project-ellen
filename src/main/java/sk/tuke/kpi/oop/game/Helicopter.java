package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.Invoke;

public class Helicopter extends AbstractActor {
    private boolean follow;
    private Player player;
    private Animation heli = new Animation("srites/heli.png", 64, 64, 10);

    public Helicopter() {
        setAnimation(heli);
        follow = false;
    }

    public void searchAndDestroy() {
        follow = true;
    }

    public void addedToScene(Scene scene, Invoke invoke, Reactor defLight) {
        new Loop<>(new Invoke(this::kill)).scheduleOn(this);
    }


    private void kill() {
        int energy = player.getEnergy();
        if (follow) {
            if (player.getPosY() > getPosY()) {
                int y = getPosY() + 1;
                setPosition(getPosX(), y);
            } else if (player.getPosY() < getPosY()) {
                int y = getPosY() - 1;
                setPosition(getPosX(), y);
            }

            if (player.getPosX() > getPosX()) {
                int x = getPosX() + 1;
                setPosition(x, getPosY());
            } else if (player.getPosX() < getPosX()) {
                int x = getPosX() - 1;
                setPosition(x, getPosY());
            }
            if (player.getPosX() == getPosX() && player.getPosY() == getPosY()) {
                player.setEnergy(energy--);
            }
        }
    }
}
