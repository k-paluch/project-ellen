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
        Actor ammo = this.getActor();
        if ((ammo != null) && (((Armed) ammo).getFirearm() != null)){
            Bullet bullet = ((Armed) ammo).getFirearm().fire();
            if ((ammo.getScene() != null) && (bullet != null)){
                ammo.getScene().addActor(bullet, ammo.getPosX() - (int)(bullet.getWidth()*bullet.getAnimation().getScale()) / 2 + ammo.getWidth() / 2, ammo.getPosY() - (int)(bullet.getHeight() * (1 - (0.5 * bullet.getAnimation().getScale()))) + (ammo.getHeight() / 2));
                ammo.getAnimation().setRotation(ammo.getAnimation().getRotation());
                new Move<>(Direction.fromAngle(ammo.getAnimation().getRotation()), Float.MAX_VALUE).scheduleOn(bullet);
            }
        }
        this.setDone(true);
    }
}
