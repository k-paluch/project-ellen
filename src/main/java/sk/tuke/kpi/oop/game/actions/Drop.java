package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop extends AbstractAction<Keeper<Collectible>> {

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull Keeper<Collectible> actor) {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

    @Override
    public void execute(float deltaTime) {
        if ((this.getActor().getContainer() != null) && (this.getActor().getScene() != null) && (this.getActor() != null)){
            Collectible first = this.getActor().getContainer().peek();
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
