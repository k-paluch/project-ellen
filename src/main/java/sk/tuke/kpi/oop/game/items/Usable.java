package sk.tuke.kpi.oop.game.items;

public interface Usable<Actor> {
    void useWith(Actor actor);
    Class<Actor> getUsingActorClass();
}
