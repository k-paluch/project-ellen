package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int ammo, int maxAmmo) {
        super(ammo, maxAmmo);
    }

    @Override
    protected Bullet createBullet() {
        Bullet bullet = new Bullet();
        return bullet;
    }


}
