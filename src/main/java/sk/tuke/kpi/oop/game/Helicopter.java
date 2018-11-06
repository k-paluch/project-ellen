package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    private boolean follow;
    private Player player;
    private Animation heli = new Animation("srites/heli.png",64,64,10);
    public Helicopter(){
        setAnimation(heli);
        follow = false;
    }

    public void searchAndDestroy(){
        follow  = true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        int energy = player.getEnergy();
        if(follow==true) {
            if(player.getPosY() > getPosY()){
                int y = getPosY()+1;
                setPosition(getPosX() , y);
            }
            else if(player.getPosY() < getPosY()){
                int y = getPosY()-1;
                setPosition(getPosX() , y);
            }

            if(player.getPosX() > getPosX()){
                int x = getPosX()+1;
                setPosition(x , getPosY());
            }
            else if(player.getPosX() < getPosX()){
                int x = getPosX()-1;
                setPosition(x , getPosY());
            }
            if(player.getPosX() == getPosX() && player.getPosY() == getPosY() ){
                energy--;
                player.setEnergy(energy);
            }


        }
    }
}
