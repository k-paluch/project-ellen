package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    private int destination;
    private Teleport teleport;
    private Animation tele = new Animation("sprites/lift.png",48,48,0);
    public Teleport(){
        setAnimation(tele);

    }

    public int getDestination(){
        return destination;
    }

    public void setDestination(Teleport destinationTeleport){
    }

    public void teleportPLayer(Player player){
        if(player.getPosY()==teleport.getPosY()){
            if(player.getPosX()==teleport.getPosX()){
                player.setPosition(teleport.getPosX(),teleport.getPosY());
            }
        }
    }
}
