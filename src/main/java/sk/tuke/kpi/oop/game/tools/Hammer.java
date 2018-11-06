package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor{
    private Animation animation;
    private int uses;

    public Hammer() {
        animation = new Animation("hammer.png",16,16);
        uses = 1;
    }
    public void use(){
        uses --;
        if(uses==0) {
            getScene().removeActor(this);
        }
    }
}