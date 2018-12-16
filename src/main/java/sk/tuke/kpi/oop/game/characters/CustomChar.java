package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;
import sk.tuke.kpi.oop.game.weapons.SuperFirearm;
import sk.tuke.kpi.oop.game.weapons.SuperGun;

public class CustomChar extends AbstractActor implements Movable, Keeper<Collectible>, Alive, Armed {

    private Backpack backpack;
    private Health health;
    private Firearm gun;
    private SuperFirearm SuperGun;
    public static final Topic<CustomChar> CustomChar_DIED = Topic.create("Your char died", CustomChar.class);

    public CustomChar() {
        super("Ellen");
        this.gun = new Gun(100, 50);
        this.SuperGun = new SuperGun(1,1);
        this.health = new Health(100);
        this.backpack = new Backpack("Ripley's backpack", 10);
        setAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.getAnimation().setRotation(0);
        this.getAnimation().pause();
    }

    public void die(){
        this.setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
        this.getAnimation().resetToFirstFrame();
        this.getAnimation().play();
    }

    public boolean isAlly(){
        return true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.getHealth().onExhaustion(() -> {
            scene.getMessageBus().publish(CustomChar_DIED, this);
            this.die();
        });
    }

    public Health setHealth(Health newHealth){
        this.health = newHealth;
        return this.health;
    }

    public int getAmmo() {
        return this.getFirearm().getAmmo();
    }

    public void setAmmo(int ammo) {
        this.getFirearm().reload(ammo);
    }


    @Override
    public void startedMoving(Direction direction) {
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().pause();
    }

    @Override
    public ActorContainer<Collectible> getContainer() {
        return this.backpack;
    }

    @Override
    public Health getHealth() {
        return this.health;
    }

    @Override
    public Firearm getFirearm() {
        return this.gun;
    }


    @Override
    public void setFirearm(Firearm weapon) {
        this.gun = weapon;
    }

}
