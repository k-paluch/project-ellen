package sk.tuke.kpi.oop.game.weapons;

public abstract class SuperFirearm {
    private int maxAmmo;
    private int ammo;

    public SuperFirearm(int ammo){
        this.ammo = ammo;
    }

    public SuperFirearm(int ammo, int maxAmmo) {
        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
    }

    public int getSuperAmmo(){
        return this.ammo;
    }

    public void reload(int newAmmo){

        if(this.ammo==maxAmmo){
            return;
        }
        int help = this.ammo + newAmmo;
        if (help>this.maxAmmo){
            this.ammo = this.maxAmmo;
        }
        else if (help < 0){
            this.ammo = 0;
        }
        else{
            this.ammo = help;
        }
    }

    protected abstract Fireable createSuperBullet();

    public Fireable fire(){
        if (this.getSuperAmmo() > 0){
            this.ammo --;
            return this.createSuperBullet();
        }
        return null;
    }
}
