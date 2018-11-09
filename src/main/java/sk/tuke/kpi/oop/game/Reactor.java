package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.BreakableTool;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature, damage, zapnute;
    private Set<EnergyConsumer> devices;
    private EnergyConsumer device = null;
    private Animation normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation extinguishedAnimation = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_PINGPONG);

    public Reactor() {
        devices = new HashSet<>();
        this.temperature = 0;
        this.damage = 0;
        turnOff();
        setAnimation(offAnimation);
    }


    public void updateAnimation() {
        if (!isOn() && getDamage() < 100){
            setAnimation(offAnimation);
        }
        else if (temperature >= 6000) {
            setAnimation(brokenAnimation);
        } else if (temperature > 4000 && temperature < 6000) {
            setAnimation(hotAnimation);
        } else {
            setAnimation(normalAnimation);
        }
    }

    public void increaseTemperature(int increment) {
        if(isOn() == false){
            return;
        }
        if(increment<0){
            return;
        }
        if (getDamage() >= 33 && getDamage() <= 66) {
            temperature += increment * 1.5;
        } else if (getDamage() > 66) {
            temperature += increment * 2;
        } else
            temperature += increment;


        if (getTemperature() <= 2000)
            return;

        if (getDamage() < (getTemperature() - 2000) / 40) {
            damage = (getTemperature() - 2000) / 40;
        }

        if (getDamage() >= 100) {
            damage = 100;
        }

        if (getTemperature() < 0) {
            temperature = 0;
        }


        updateAnimation();


    }

    public void decreaseTemperature(int decrement) {
        if(decrement<0) return;
        if(getDamage() < 100 && decrement > 0 && isOn()){
            if(getDamage() >= 50){
                this.temperature -= decrement/2;
            }
            else{ this.temperature -= decrement;}
            updateAnimation();
            if(this.temperature<0){
                setTemperature(0);
            }
        }

    }

    public int getTemperature() {

        return temperature;
    }

    public int getDamage() {

        return damage;
    }

    public void turnOn() {
        zapnute = 1;

        updateAnimation();
        if(device!=null){
            device.setPowered(true);
            updateDevices();
        }
    }

    public void turnOff() {
        zapnute = 0;
        updateDevices();
        updateAnimation();
        if(device!=null){
            device.setPowered(false);
            updateDevices();
        }
    }

    public boolean isOn() {
        if (zapnute == 1) {
            return true;
        } else if (zapnute == 0) {
            return false;
        } else return false;
    }


    public void setTemperature(int newTemp) {
        this.temperature = newTemp;
    }

    public void setDamage(int newDam) {
        if(newDam > 0 && newDam < 100)
        {
            this.damage = newDam;
        }
        else if(newDam >= 100){
            this.damage = 100;
            turnOff();
        }
        else if( newDam <= 0){
            this.damage = 0;
        }

    }


    public void addDevice(EnergyConsumer device) {
        if (device == null)
            return;
        if (isOn()) {
            device.setPowered(true);
        }
        devices.add(device);
    }


    public void removeDevice(EnergyConsumer device) {
        if (device == null) return;
            devices.remove(device);
            device.setPowered(false);
    }

    public boolean extinguish(FireExtinguisher fireExtinguisher) {
        if (fireExtinguisher == null) return false;
        if (damage == 100 && temperature > 4000) {
            this.temperature = 4000;
            setAnimation(extinguishedAnimation);
            fireExtinguisher.useWith(this);
            return true;
        } else return false;
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
    }

    public void updateDevices() {
        if (this.devices != null) {
            for (EnergyConsumer device : devices
            ) {
                device.setPowered(this.isOn());
            }
        }
    }

    public boolean repair(){
        return false;
    }

    @Override
    public boolean repair(BreakableTool hammer) {
        if (hammer == null) {
            return false;
        }
        if (hammer instanceof Hammer && damage < 100 && damage > 0) {
            int tempDamage = damage-50;
            if(this.damage>50){
                this.damage -=50;
            }
            else{this.damage = 0;}
            updateAnimation();
            return true;
        }
        return false;
    }
}
