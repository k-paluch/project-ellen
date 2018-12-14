package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;

public class Teleport extends AbstractActor {

    private Teleport destination;
    private boolean zamka;

    public Teleport(Teleport destination) {
        this.destination = destination;
        setAnimation(new Animation("sprites/lift.png", 48, 48));
    }

    private Player getPlayer(){
        if (this.getScene() != null){
            return (Player) this.getScene().getFirstActorByName("Player");
        }
        else{
            return null;
        }
    }

    private boolean intersectsWithPlayer(Player player){
        if (player == null){
            return false;
        }
        int playerX = player.getPosX() + player.getWidth() / 2;
        int playerY = player.getPosY() + player.getHeight() / 2;
        Rectangle2D.Float playerAnimRec = new Rectangle2D.Float(playerX, playerY, 1, 1);
        return playerAnimRec.intersects(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new When<>(
            action -> {
                Player player = this.getPlayer();
                return (this.destination != null) && (!this.zamka) && (this.intersectsWithPlayer(player));
            },
            new Invoke<>(() -> {
                this.destination.teleportPlayer(this.getPlayer());
            })
        )).scheduleOn(this);
    }

    public Teleport getDestination() {
        return destination;
    }
    public void setDestination(Teleport destination){
        if (this != destination){
            this.destination = destination;
        }
    }

    public void activateLock() {
        this.zamka = true;
        new When<>(
            action -> !this.intersectsWithPlayer(this.getPlayer()),
            new Invoke<>(this::deactivateLock)
        ).scheduleOn(this);
    }

    public void deactivateLock(){
        this.zamka = false;
    }

    public void teleportPlayer(Player player) {
        if (player != null) {
            this.activateLock();
            player.setPosition(
                (this.getPosX() + (this.getWidth() - player.getWidth()) / 2),
                (this.getPosY() + (this.getHeight() - player.getHeight()) / 2)
            );
        }
    }
}
