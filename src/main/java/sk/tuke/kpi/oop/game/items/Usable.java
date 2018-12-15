package sk.tuke.kpi.oop.game.items;

public interface Usable<A> {
    void useWith(A actor);
    Class<A> getUsingActorClass();
}
