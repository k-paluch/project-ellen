package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int maxAmmo, int ammo) {
        super(maxAmmo, ammo);
    }

    @Override
    protected Bullet createBullet() {
        Bullet bullet = new Bullet();
        return bullet;
    }


}
