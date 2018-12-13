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

public class CollectorController implements KeyboardListener {
    Usable<?> findFirstUsableActor(){
        if (this.actor.getScene() == null) return null;
        Class<Usable> findingClass = Usable.class;
        for (Actor aktor : this.actor.getScene().getActors()){
            if (findingClass.isInstance(aktor) && aktor.intersects(this.actor)){
                return findingClass.cast(aktor);
            }
            else
                return null;
        }
        return null;
    }

    private Keeper<Collectible> actor;
    public CollectorController(Keeper<Collectible> onActor) {
        this.actor = onActor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        switch (key){
            case ENTER:
                new Take<>(Collectible.class).scheduleOn(this.actor);
                break;
            case BACKSPACE:
                new Drop().scheduleOn(this.actor);
                break;
            case S:
                new Shift().scheduleOn(this.actor);
                break;
            case U:
                Usable<?> usable = this.findFirstUsableActor();
                if (usable == null)
                    break;
                else
                    new Use(usable).scheduleOnIntersectingWith(this.actor);
                break;
            case B:
                if (this.actor.getContainer().getSize() > 0){
                    Collectible peek = this.actor.getContainer().peek();
                    if (peek instanceof Usable){
                        if (new Use((Usable<?>)peek).scheduleOnIntersectingWith(this.actor) != null){
                            this.actor.getContainer().remove(peek);
                        }
                    }
                }
            default:
                break;
        }
    }
}
