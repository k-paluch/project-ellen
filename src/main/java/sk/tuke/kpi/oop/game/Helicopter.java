package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.Invoke;

public class Helicopter extends AbstractActor {
    private boolean follow = false;
    private Animation helicopter = new Animation("sprites/heli.png", 64, 64, 0.1f,Animation.PlayMode.LOOP_PINGPONG);

    public Helicopter() {
        setAnimation(helicopter);
    }

    public void searchAndDestroy() {
        follow = true;
    }

    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::chase)).scheduleOn(this);


    }


    public void chase() {
        Player player = (Player) getScene().getFirstActorByName("Player");
        int energy = player.getEnergy();
        int y,x;
        if(follow==true) {
            if(player.getPosY() > getPosY()){
                y = getPosY()+1;
                setPosition(getPosX() , y);
            }
            else if(player.getPosY() < getPosY()){
                y = getPosY()-1;
                setPosition(getPosX() , y);
            }

            if(player.getPosX() > getPosX()){
                x = getPosX()+1;
                setPosition(x , getPosY());
            }
            else if(player.getPosX() < getPosX()){
                x = getPosX()-1;
                setPosition(x , getPosY());
            }
            if(player.getPosX() == getPosX() && player.getPosY() == getPosY() ){
                energy--;
                player.setEnergy(energy);
            }


        }
    }

}
