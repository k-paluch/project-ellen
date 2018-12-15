package sk.tuke.kpi.oop.game.weapons;

public class SuperGun extends SuperFirearm {

    public SuperGun(int maxAmmo, int SuperAmmo) {
        super(maxAmmo, SuperAmmo);
    }


    @Override
    protected SuperBullet createSuperBullet() {
        return new SuperBullet();
    }

}
