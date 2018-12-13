package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {


    public Locker() {
        super("locker");
        setAnimation(new Animation("sprites/locker.png", 16, 16));
    }

    @Override
    public void useWith(Ripley actor) {
        if ((this.getScene() != null) && (actor.intersects(this))){
            this.getScene().addActor(new Hammer(), this.getPosX(), this.getPosY());
            this.getScene().removeActor(this);
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
