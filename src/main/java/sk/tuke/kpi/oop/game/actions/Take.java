package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Take<T extends Actor> extends AbstractAction<Keeper<T>> {


    private Class<T> vzatelne;
    Keeper<T> actor ;
    public Take(Class<T> vzatelnyactor) {
        this.vzatelne = vzatelnyactor;
    }

    @Override
    public void execute(float deltaTime) {
        Keeper<T> ripley = getActor();
        if(ripley != null) {
            Scene scene = ripley.getScene();
            try {
                for (Actor item : scene.getActors()) {
                    if (ripley.intersects(item) && vzatelne.isInstance(item) && actor.getContainer().getCapacity() != actor.getContainer().getSize()) {
                        actor.getContainer().add(vzatelne.cast(item));
                        scene.removeActor(item);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Backpack is full");
            }
        }
        setDone(true);
    }

    @Nullable
    @Override
    public Keeper<T> getActor() {
        return super.getActor();
    }

    @Override
    public void setActor(@Nullable Keeper<T> actor) {
        this.actor = actor;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull Keeper<T> actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }
}
