package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

public class ChainBomb extends TimeBomb {

    public ChainBomb(float detonationTime) {
        super(detonationTime);
    }

   @Override
    protected void onExplode() {

        Ellipse2D.Float thisElipse = new Ellipse2D.Float(
            this.getPosX() + ((float)getWidth() / 2) - 50,
            this.getPosY() + ((float)getHeight() / 2) - 50, 50 * 2, 50 * 2);
        List<Actor> actors = Objects.requireNonNull(this.getScene()).getActors();
        for (Actor actor: actors) {
           if (actor instanceof ChainBomb){
               Rectangle2D.Float anotherRectangle =
                   new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getWidth(), actor.getHeight());
               if (thisElipse.intersects(anotherRectangle) && !((ChainBomb) actor).isActivated()){
                   ((ChainBomb) actor).activate();
               }
           }
        }
    }

}
