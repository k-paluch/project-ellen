package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.BreakableTool;
import sk.tuke.kpi.oop.game.tools.Wrench;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {
    private Random random;
    private int cas;
    private boolean oprava,powered ;
    private Animation light_off = new Animation("sprites/light_off.png", 16, 16, 10);
    private Animation light_on = new Animation("sprites/light_on.png", 16, 16, 10);
    public DefectiveLight(){
        super();
        random = new Random();
        cas = 0;
        oprava = false;
    }

    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::defLight)).scheduleOn(this);
    }


    private void defLight() {
        if (!oprava) {
            int number = random.nextInt(2) + 1;
            if (number == 1) {
                toggle();
            }
        }
    }

    @Override
    public boolean repair(BreakableTool tool) {
        if(tool instanceof Wrench){
            tool.use();
            if(powered){
            setAnimation(light_on);
            }
            else setAnimation(light_off);
            oprava = true;
            return true;
        }
        return false;
    }

    @Override
    public void setPowered(boolean isPowered) {
        if (reactor.isOn()) {
            powered= true;
            setPowered(isPowered);
        }
    }
}
