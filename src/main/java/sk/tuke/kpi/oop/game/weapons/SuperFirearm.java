package sk.tuke.kpi.oop.game.weapons;

public abstract class SuperFirearm {

    private int maxAmmo;
    private int ammo;

    public SuperFirearm(int maxAmmo, int ammo) {
        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
    }

    public int getAmmo(){
        return this.ammo;
    }

    public void reload(int newAmmo){
        this.ammo = Math.max(1,this.ammo + newAmmo);

        if (this.ammo < 0){
            this.ammo = 0;
        }
    }

    protected abstract SuperBullet createSuperBullet();
    public SuperBullet SuperFire(){
        if (this.getAmmo() > 0){
            this.reload(-1);
            return this.createSuperBullet();
        }
        return null;
    }

}
