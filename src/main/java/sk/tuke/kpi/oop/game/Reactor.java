package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {

    private int temperature;
    private int damage;
    private boolean isTurnedOn;
    private Set<EnergyConsumer> devices;
    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation extinguishedAnimation;

    public Reactor(){

        this.temperature = 0;
        this.damage = 0;
        this.isTurnedOn = false;
        this.devices = new HashSet<>();
        this.offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.extinguishedAnimation = new Animation("sprites/reactor_extinguished.png", 80, 80);
        this.updateAnimation();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
    }

    @Override
    public boolean isOn() {
        return isTurnedOn;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int increment){
        if ((increment < 0) || (this.damage >= 100) || (!this.isOn()))
            return;
        if (this.damage < 33){
            this.temperature += increment;
        } else if ((this.damage <= 66)){
            this.temperature += Math.ceil((float)(increment * 1.5));
        } else {
            this.temperature += increment * 2;
        }
        if (this.getTemperature() > 2000){
            int damage = (int)Math.floor((float)((this.getTemperature() - 2000) / 40));
            if (this.damage < damage) {
                this.setDamage(damage);
            }
        }
        if (this.getDamage() == 100){
            this.turnOff();
        }
        this.updateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if ((decrement < 0) || (this.damage >= 100) || (!this.isOn()))
            return;
        if ((this.damage >= 50) && (decrement >= 2)){
            this.temperature -= Math.ceil((float)(decrement / 2));
        } else {
            this.temperature -= decrement;
        }
        this.updateAnimation();
    }

    private void updateAnimation(){
        if (!this.isOn() && (this.getDamage() != 100)) {
            setAnimation(this.offAnimation);
        } else {
            if (this.getTemperature() >= 6000) {
                setAnimation(this.brokenAnimation);
            } else if (this.getTemperature() > 4000) {
                setAnimation(this.hotAnimation);
            } else {
                setAnimation(this.normalAnimation);
            }
        }
    }

    private void setDamage(int damage) {
        if (damage < 0){
            this.damage = 0;
        } else if (damage > 100){
            this.damage = 100;
        } else {
            this.damage = damage;
        }
    }

    private void setTemperature(int temperature) {
        this.temperature = temperature;
        this.updateAnimation();
    }

    public boolean extinguish(){
        if (this.damage < 100){
            return false;
        }
        this.setTemperature(4000);
        setAnimation(this.extinguishedAnimation);
        return true;
    }

    private void updateConsumerPowerState(){
        for (EnergyConsumer e : this.devices) {
            e.setPowered((this.getDamage() < 100) && (this.isOn()));
        }
    }

    @Override
    public void turnOn(){
        if (this.damage < 100){
            this.isTurnedOn = true;
            this.updateConsumerPowerState();
            this.updateAnimation();
        }
    }

    @Override
    public void turnOff(){
        this.isTurnedOn = false;
        this.updateConsumerPowerState();
        this.updateAnimation();
    }

    public void addDevice(EnergyConsumer energyConsumer){
        this.devices.add(energyConsumer);
        energyConsumer.setPowered((this.getDamage() < 100) && (this.isOn()));
    }

    public void removeDevice(EnergyConsumer energyConsumer){
        this.devices.remove(energyConsumer);
        energyConsumer.setPowered(false);
    }

    @Override
    public boolean repair() {
        if (damage <= 0) {
            return false;
        }
        int damage = this.getDamage() - 50;
        int temperature = 2000 + (damage * 40);
        this.setDamage(this.getDamage() - 50);
        if (temperature < this.getTemperature()){
            setTemperature(temperature);
        }
        return true;
    }
}
