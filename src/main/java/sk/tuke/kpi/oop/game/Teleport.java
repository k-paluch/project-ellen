package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;

public class Teleport extends AbstractActor {
    private Teleport destination = null;
    private boolean tp;
    private Animation animation = new Animation("sprites/lift.png", 48, 48, 10);
    private Player player = null;

    public Teleport(Teleport destination) {
        if (this != destination)
            setDestination(destination);
        setAnimation(animation);
        setTp(true);
    }

    public void teleportPlayer(Player player) {
        if (destination != null && player != null) {
            destination.setTp(true);
            player.setPosition(destination.getPosX() + (destination.getWidth() / 2) - (player.getWidth() / 2), destination.getPosY() + (destination.getHeight() / 2) - (player.getHeight() / 2));
        }
    }

    public void setDestination(Teleport destination) {
        if (this != destination)
            this.destination = destination;
    }

    public Teleport getDestination() {
        return this.destination;
    }

    public void setTp(boolean teleport) {
        this.tp = teleport;
    }

    public boolean isTp() {
        return this.tp;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (destination != null) {
            new Loop<>(new Invoke(this::teleportWhenIntersect)).scheduleOn(this);
        }
    }

    private void teleportWhenIntersect() {
        if (this.player == null) {
            for (Actor actor : this.getScene().getActors()
            ) {
                if (actor instanceof Player) {
                    player = (Player) actor;
                }
            }
        }
        Rectangle2D rec = new Rectangle2D.Float(player.getPosX() + player.getWidth() / 2, player.getPosY() + getHeight() / 2, 1, 1);

        if (player != null && rec.intersects(getPosX(), getPosY(), getWidth(), getHeight())) {
            teleportPlayer(player);
        }
    }

}
