package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.concurrent.atomic.AtomicInteger;

public class Helicopter extends AbstractActor {

    public Helicopter() {

        setAnimation(new Animation(
            "sprites/heli.png",
            64,
            64,
            0.02F,
            Animation.PlayMode.LOOP_PINGPONG
        ));
    }

    private Player getPlayer(){
        if (this.getScene() != null){
            return (Player)this.getScene().getFirstActorByName("Player");
        }

        return null;
    }

    private void setRotation(int rotation){

        if (rotation < 0){
            return;
        }

        this.getAnimation().setRotation(rotation);
    }

    public void searchAndDestroy(){

        new Loop<>(new Invoke(this::run)).scheduleOn(this);
    }

    private void run() {
        Player player = this.getPlayer();

        if (player != null) {

            int x = player.getPosX();
            int y = player.getPosY();

            AtomicInteger rotation = new AtomicInteger();
            int nextX, nextY;

            if (x > this.getPosX()) {
                nextX = this.getPosX() + 1;
                rotation.set(270);
            } else {
                nextX = this.getPosX() - 1;
                rotation.set(90);
            }

            if (y > this.getPosY()) {
                nextY = this.getPosY() + 1;
                if (Math.abs(x - this.getPosX()) < Math.abs(y - this.getPosY()))
                    rotation.set(0);
            } else {
                nextY = this.getPosY() - 1;
                if (Math.abs(x - this.getPosX()) < Math.abs(y - this.getPosY()))
                    rotation.set(180);
            }

            this.setRotation(rotation.get());
            this.setPosition(nextX, nextY);

            if (this.intersects(player)) {
                player.setEnergy(player.getEnergy() - 1);
            }
        }
    }
}
