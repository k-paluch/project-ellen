package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int maxAmmo;
    private int ammo;

    public Firearm(int ammo){
        this.ammo = ammo;
    }

    public Firearm(int ammo, int maxAmmo) {
        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
    }

    public int getAmmo(){
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

    protected abstract Fireable createBullet();

    public Fireable fire(){
        if (this.getAmmo() > 0){
            this.ammo --;
            return this.createBullet();
        }
        return null;
    }

}
