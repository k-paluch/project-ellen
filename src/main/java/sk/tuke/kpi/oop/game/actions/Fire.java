package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Bullet;

public class Fire<A extends Armed> extends AbstractAction<A> implements Action<A> {
    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull A actor) {
        if (actor.getScene() != null) {
            this.setActor(actor);
            return super.scheduleOn(actor);
        }
        throw new IllegalStateException("Scene not associated with actor !");
    }

    @Override
    public void execute(float deltaTime) {
        Armed actor = this.getActor();
        if ((actor != null) && (actor.getFirearm() != null)){
            Bullet bullet = actor.getFirearm().fire();
            if ((actor.getScene() != null) && (bullet != null)){
                actor.getScene().addActor(bullet, actor.getPosX() - (int)(bullet.getWidth()*bullet.getAnimation().getScale()) / 2 + actor.getWidth() / 2, actor.getPosY() - (int)(bullet.getHeight() * (1 - (0.5 * bullet.getAnimation().getScale()))) + (actor.getHeight() / 2));
                actor.getAnimation().setRotation(actor.getAnimation().getRotation());
                new Move<>(Direction.fromAngle(actor.getAnimation().getRotation()), Float.MAX_VALUE).scheduleOn(bullet);
            }
        }
        this.setDone(true);
    }
}
