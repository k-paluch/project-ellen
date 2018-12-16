package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Drop<A extends Actor> extends AbstractAction<Keeper<A>> {

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull Keeper<A> actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

    @Override
    public void execute(float deltaTime) {
        if(this.getActor()==null){
            this.setDone(true);
            return;
        }
        if ((this.getActor().getContainer() != null) && (this.getActor().getScene() != null) && (this.getActor() != null)){
            A first = this.getActor().getContainer().peek();
            if (first != null) {
                this.getActor().getContainer().remove(first);
                int PX = this.getActor().getPosX() + this.getActor().getWidth() / 2;
                int PY = this.getActor().getPosY() + this.getActor().getHeight() / 2;
                this.getActor().getScene().addActor(first, PX - (first.getWidth() / 2), PY - (first.getHeight() / 2));
                this.setDone(true);
            }
        }
        this.setDone(true);
    }
}
