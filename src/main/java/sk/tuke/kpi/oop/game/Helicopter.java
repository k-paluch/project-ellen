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
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::kill)).scheduleOn(this);
    }


    public void kill()
    {
        Player player = (Player) getScene().getFirstActorByName("Player");
        int x = this.getPosX(), y = this.getPosY();

        if(follow){
            if (player.getPosX() < this.getPosX()) {
                x = x - 1;
            }
            else if (player.getPosX() > this.getPosX()) {
                x = x + 1;
            }
            if (player.getPosY() < this.getPosY()) {
                y = y - 1;
            }
            else if (player.getPosY() > this.getPosY()) {
                y = y + 1;
            }
            setPosition(x, y);
            if (player.intersects(this)) {
                player.setEnergy(player.getEnergy()-1);
            }
        }
    }

}
