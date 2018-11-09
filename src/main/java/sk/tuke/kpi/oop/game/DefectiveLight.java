package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {
    private Random random = new Random();
    private boolean is_Powered;
    private int time = 100000;
    private boolean oprava = false;

    public DefectiveLight() {

    }

    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::defLight)).scheduleOn(this);
    }

    @Override
    public void setPowered(boolean isPowered) {
        is_Powered = isPowered;
        super.setPowered(isPowered);
    }


    private void defLight() {
        if (!oprava && is_Powered) {
            int number = random.nextInt(20);
            if (number == 1) {
                super.toggle();
            }
        }
        if (oprava&&is_Powered) {
                this.time--;
                if (this.time <= 0) {
                    this.oprava = false;
                    this.time = 100000;
                } else super.turnOff();
        }


    }
    public boolean repair() {
        if (oprava) {
            return false;
        } else {
            oprava = true;
            this.turnOn();
        }
        return true;
    }

}
