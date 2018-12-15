package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Bullet;

public class Fire<T extends Actor & Armed> extends AbstractAction<T> {
    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor) {
        if (actor.getScene() != null) {
            this.setActor(actor);
            return super.scheduleOn(actor);
        }
        throw new IllegalStateException("Scene not associated with actor !");
    }

    @Override
    public void execute(float deltaTime) {
        Actor municia = this.getActor();
        if ((municia != null) && (((Armed) municia).getFirearm() != null)){
            Bullet naboj = ((Armed) municia).getFirearm().fire();
            if ((municia.getScene() != null) && (naboj != null)){
                municia.getScene().addActor(naboj, municia.getPosX() - (int)(naboj.getWidth()*naboj.getAnimation().getScale()) / 2 + municia.getWidth() / 2, municia.getPosY() - (int)(naboj.getHeight() * (1 - (0.5 * naboj.getAnimation().getScale()))) + (municia.getHeight() / 2));
                municia.getAnimation().setRotation(municia.getAnimation().getRotation());
                new Move<>(Direction.fromAngle(municia.getAnimation().getRotation()), Float.MAX_VALUE).scheduleOn(naboj);
            }
        }
        this.setDone(true);
    }
}
