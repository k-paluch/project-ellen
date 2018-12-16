package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
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
         if ((this.getActor() != null) &&  (this.getActor().getScene() != null) && (this.getActor().getContainer() != null)){
             for (Actor a : this.getActor().getScene().getActors()){
                 if (actor.intersects(actor) && vzatelne.isInstance(actor) && actor.getContainer().getCapacity() != actor.getContainer().getSize()){
                     /*try {*/
                         this.getActor().getContainer().add(this.vzatelne.cast(a));
                         this.getActor().getScene().removeActor(a);
                     /*}
                     catch (IllegalStateException e){
                         this.getActor().getScene().getOverlay().drawText(e.getMessage(), 0, 50).showFor(2);
                     }*/
                 }
             }
         }
         this.setDone(true);
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
