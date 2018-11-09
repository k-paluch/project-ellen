package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.tools.BreakableTool;
import sk.tuke.kpi.oop.game.tools.Wrench;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {
    private Random random = new Random();
    private boolean is_Powered;
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
        if (oprava) {
            super.turnOn();
            new Thread(() -> {
                long sec = 10;
                long startTime = System.currentTimeMillis();
                for (int count = 0; ; count++) {
                    long now = System.currentTimeMillis();
                    if (now - startTime >= sec * 1000){
                        break;
                    }
                }
                oprava= false;
            }).start();

        }
    }

    @Override
    public boolean repair(BreakableTool actor) {
        if (actor == null) return false;
        if(oprava){
            return false;
        }
        if (actor instanceof Wrench) {
            actor.useWith(this);
            oprava = true;
            return true;
        }
        return false;
    }

}
