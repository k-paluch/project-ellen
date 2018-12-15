package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Gun;
import sk.tuke.kpi.oop.game.weapons.SuperBullet;

public class SuperFire<T extends Actor & Armed<Gun>> extends AbstractAction<T> {
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
        Actor SuperAmmo = this.getActor();
        if ((SuperAmmo != null) && (((Armed) SuperAmmo).getSuperFirearm() != null)){
            SuperBullet bullet = ((Armed) SuperAmmo).getSuperFirearm().SuperFire();
            if ((SuperAmmo.getScene() != null) && (bullet != null)){
                SuperAmmo.getScene().addActor(bullet, SuperAmmo.getPosX() - (int)(bullet.getWidth()*bullet.getAnimation().getScale()) / 2 + SuperAmmo.getWidth() / 2, SuperAmmo.getPosY() - (int)(bullet.getHeight() * (1 - (0.5 * bullet.getAnimation().getScale()))) + (SuperAmmo.getHeight() / 2));
                SuperAmmo.getAnimation().setRotation(SuperAmmo.getAnimation().getRotation());
                new Move<>(Direction.fromAngle(SuperAmmo.getAnimation().getRotation()), Float.MAX_VALUE).scheduleOn(bullet);
            }
        }
        this.setDone(true);
    }
}
