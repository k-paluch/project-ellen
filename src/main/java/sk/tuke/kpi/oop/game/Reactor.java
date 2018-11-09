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

import static java.lang.Math.max;

public class Reactor extends AbstractActor implements Switchable,Repairable {
    private int temperature, damage,zapnute;
    private Set<EnergyConsumer> devices;
    private EnergyConsumer device;
    private Animation normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation extinguishedAnimation = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.0f, Animation.PlayMode.LOOP_PINGPONG);
    public Reactor(){
        devices = new HashSet<>();
        this.temperature =0;
        this.damage=0;
        turnOff();
        setAnimation(offAnimation);
    }



    public void updateAnimation(){
        if(!isOn()) {
            if (damage == 100) {
                setAnimation(brokenAnimation);
            }
            else{
                setAnimation(offAnimation);
            }
        }
        else{
            if (damage == 100) {
                setAnimation(brokenAnimation);
            }
            else if(temperature>4000){
                setAnimation(hotAnimation);
            }
            else{
                setAnimation(normalAnimation);
            }
        }
    }
    public void increaseTemperature(int increment) {
        if(getDamage()>=6000 || getTemperature()>=100){
            return;
        }
        if(!isOn()){
            return;
        }
        if (increment < 0) {
            return;
        }
        this.temperature += increment;
        if (getDamage()> 33 && getDamage() < 66) {
            temperature = (int) max(6000,((1.5*increment)+temperature));
           setDamage(max(100, ((this.temperature - 2000) / 4000) * 100));
        } else if (this.damage > 66) {
            temperature = max(6000,(2*increment)+temperature);
            this.damage = max(100, ((this.temperature - 2000) / 4000) * 100);
        }

        temperature = max(6000,increment+temperature);
        this.damage = max(100, ((this.temperature - 2000) / 4000) * 100);
        updateAnimation();
    }

    public void decreaseTemperature(int decrement) {
        if(getDamage()>=6000 || getTemperature()>=100){
            return;
        }
        if(isOn()){
            return;
        }
        if(decrement < 0) {
            return;
        }
        if (getDamage() > 50) {
            this.temperature = max(0,temperature-(decrement)*2);
        }
        if (damage == 100) {
            this.temperature = max(0,temperature-(decrement)*0);
        }
        this.temperature = max(0,temperature-decrement);
        updateAnimation();
    }

    public int getTemperature() {

        return temperature;
    }

    public int getDamage() {

        return damage;
    }

    public void turnOn(){
        zapnute = 1;
        updateDevices();
        updateAnimation();
        device.setPowered(true);
    }

    public void turnOff() {
        zapnute = 0;
        updateDevices();
        updateAnimation();
        device.setPowered(false);
    }

    public boolean isOn(){
        if(zapnute == 1){
            return true;
        }
        else if(zapnute == 0) {
            return false;
        }
        else return false;
    }


    public void setTemperature(int newTemp){
        this.temperature = newTemp;
    }
    public void setDamage (int newDam){
        this.damage = newDam;
    }


    public void addDevice(EnergyConsumer device) {
        if(device == null)
            return;
        if(isOn()){
            device.setPowered(true  );
        }
        devices.add(device);
    }


    public void removeDevice(EnergyConsumer device){
        if(device==null) return;
        if(devices.contains(device)) {
            devices.remove(device);
            device.setPowered(false);
        }
    }

    public boolean extinguish(FireExtinguisher fireExtinguisher) {
        if(fireExtinguisher==null) return false;
        if (damage == 100&&temperature>4000) {
            this.temperature = 4000;
            setAnimation(extinguishedAnimation);
            fireExtinguisher.useWith(this);
            return true;
        }
        else return false;
    }
    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
    }

    public void updateDevices(){
        if(this.devices != null){
            for (EnergyConsumer device: devices
            ) {
                device.setPowered(this.isOn());
            }
        }
    }

    @Override
    public boolean repair(BreakableTool hammer) {
        if(hammer == null){
            return false;
        }
        if(hammer instanceof Hammer && damage < 100 && damage > 0){
            this.damage = max(0,this.damage-50);
            hammer.useWith(this);
            temperature = (damage - 50) * 40 + 2000  > temperature ? temperature : (damage - 50) * 40 + 2000;
            damage = damage - 50 < 0 ? 0 : damage - 50;
            updateAnimation();
            return true;
        }
        return false;
    }

}
