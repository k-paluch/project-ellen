package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends AbstractActor> extends AbstractAction<T> {

    private Usable<T> usable;

    public Use(Usable<T> usable) {
        this.usable = usable;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

    public Disposable scheduleOnIntersectingWith(Actor mediatingActor) {
        Scene scene = mediatingActor.getScene();
        if (scene == null) return null;
        Class<T> usingActorClass = usable.getUsingActorClass();
        for (Actor actor : scene) {
            if (mediatingActor.intersects(actor) && usingActorClass.isInstance(actor)) {
                return this.scheduleOn(usingActorClass.cast(actor));
            }
        }
        return null;
    }

    @Override
    public void execute(float deltaTime) {
        if (this.getActor() != null){
            System.out.print("Executing Use action !");
            this.usable.useWith(this.getActor());
        }
        this.setDone(true);
    }
}
