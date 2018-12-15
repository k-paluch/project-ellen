package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

public interface Usable<T extends Actor> extends Actor {
    void useWith(T actor);
    Class<T> getUsingActorClass();
}
