package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends Actor> extends AbstractAction<T> {

    private Usable<T> usable;

    public Use(Usable<T> usable) {
        this.usable = usable;
    }

    @Override
    public void execute(float deltaTime) {

        if (this.getActor() != null){
            System.out.print("Executing Use action !");
            this.usable.useWith(this.getActor());
        }
        this.setDone(true);
    }

    public Disposable scheduleOnIntersectingWith(Actor pracujuciActor) {
        Scene scene = pracujuciActor.getScene();
        if (scene == null) return null;
        Class<T> pouzivanyActor = usable.getUsingActorClass();
        for (Actor actor : scene) {
            if (pracujuciActor.intersects(actor) && pouzivanyActor.isInstance(actor)) {
                return this.scheduleOn(pouzivanyActor.cast(actor));
            }
        }
        return null;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor) {

        this.setActor(actor);
        return super.scheduleOn(actor);
    }
}
