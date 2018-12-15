package sk.tuke.kpi.oop.game.weapons;

public class SuperGun extends SuperFirearm {

    public SuperGun(int maxAmmo, int ammo) {
        super(maxAmmo, ammo);
    }


    @Override
    protected SuperBullet createSuperBullet() {
        return new SuperBullet();
    }

}
