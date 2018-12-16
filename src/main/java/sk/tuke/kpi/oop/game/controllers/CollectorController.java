package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.List;

public class CollectorController implements KeyboardListener {

    private Keeper<Collectible> actor;

    public CollectorController(Keeper<Collectible> onActor) {
        this.actor = onActor;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void keyPressed(@NotNull Input.Key key) {

        switch (key){
            case ENTER:
                new Take<>(Collectible.class).scheduleOn(this.actor);
                break;
            case BACKSPACE:
                new Drop<Collectible>().scheduleOn(actor);
                break;
            case S:
                new Shift().scheduleOn(this.actor);
                break;
            case U:
                List<Actor> collectorItems = actor.getScene().getActors();
                for (Actor actor : collectorItems) {
                    if (actor.intersects(actor) && actor instanceof Usable) {
                        new Use<>((Usable<?>) actor).scheduleOnIntersectingWith(actor);
                    }
                }
                break;
            case B:
                if (this.actor.getContainer().getSize() > 0){
                    Collectible nahladnutie = this.actor.getContainer().peek();
                    if ((nahladnutie instanceof Usable)&& (new Use<>((Usable)(nahladnutie)).scheduleOnIntersectingWith(this.actor) != null)){
                            this.actor.getContainer().remove(nahladnutie);
                    }
                }
                break;
            default:
                return;
        }
    }

}
