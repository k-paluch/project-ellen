package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int maxAmmo;
    private int ammo;

    public Firearm(int ammo){
        this.ammo = ammo;
    }

    public Firearm(int maxAmmo, int ammo) {
        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
    }

    public int getAmmo(){
        return this.ammo;
    }

    public void reload(int newAmmo){
        if (this.ammo < 0){
            this.ammo = 0;
        }
        this.ammo = Math.max(this.maxAmmo,this.ammo+newAmmo);
    }

    protected abstract Bullet createBullet();

    public Bullet fire(){
        if (this.getAmmo() > 0){
            this.reload(-1);
            return this.createBullet();
        }
        return null;
    }

}
