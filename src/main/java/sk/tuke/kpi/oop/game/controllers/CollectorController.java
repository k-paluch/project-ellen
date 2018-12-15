package sk.tuke.kpi.oop.game.controllers;

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

    private Keeper<Collectible> actor;

    public CollectorController(Keeper<Collectible> onActor) {
        this.actor = onActor;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void keyPressed(Input.Key key) {

        switch (key){
            case ENTER:
                new Take<>(Collectible.class).scheduleOn(this.actor);
                break;
            case BACKSPACE:
                new Drop<>().scheduleOn(this.actor);
                break;
            case S:
                new Shift().scheduleOn(this.actor);
                break;
            case U:
                Collectible nahlad = this.actor.getContainer().peek();
                Usable<?> usable = this.findFirstUsableActor();
                if (usable == null)
                    break;
                else
                    if(nahlad!= null) {
                        new Use<>((Usable)(nahlad)).scheduleOnIntersectingWith(this.actor);
                    }
                break;
            case B:
                if (this.actor.getContainer().getSize() > 0){
                    Collectible nahladnutie = this.actor.getContainer().peek();
                    if ((nahladnutie instanceof Usable)&& (new Use<>((Usable)(nahladnutie)).scheduleOnIntersectingWith(this.actor) != null)){
                            this.actor.getContainer().remove(nahladnutie);
                    }
                }
            default:
                return;
        }
    }

    private Usable<?> findFirstUsableActor(){
        if (this.actor.getScene() == null) return null;

        Class<Usable> findingClass = Usable.class;

        for (Actor a : this.actor.getScene().getActors()){
            if (findingClass.isInstance(a) && a.intersects(this.actor)){
                return findingClass.cast(a);
            }
        }
        return null;
    }
}
