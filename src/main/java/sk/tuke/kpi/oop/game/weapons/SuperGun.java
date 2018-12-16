package sk.tuke.kpi.oop.game.weapons;

public class SuperGun extends SuperFirearm {
    public SuperGun(int ammo, int maxAmmo) {
        super(ammo, maxAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return null;
    }
}
