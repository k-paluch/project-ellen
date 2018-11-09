package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

import static java.lang.Math.abs;

public class ChainBomb extends TimeBomb {

    public ChainBomb(float sec) {
        super(sec);

    }

    private void chainreaction(){
        super.reaction();
            for (Actor actor : getScene().getActors()) {
                //if (new Ellipse2D.Float(getPosX()+8, getPosY()+8, 50,50).intersects(
                    //new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getAnimation().getWidth(), actor.getAnimation().getHeight())))
                    if (actor instanceof ChainBomb) {
                        if(abs(actor.getPosX()-this.getPosX())<= 50 && (abs(actor.getPosY()-this.getPosY())<=50)) {
                            ((ChainBomb) actor).activate();
                        }
                    }
            }
    }
}
