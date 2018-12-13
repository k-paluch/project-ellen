package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift extends AbstractAction<Keeper<?>> {
    @Override
    public void execute(float deltaTime) {
        if ((this.getActor() != null) && (this.getActor().getScene() != null) && (this.getActor().getContainer() != null)) {
            this.getActor().getContainer().shift();
        }
        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull Keeper actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }
}
