package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.tools.BreakableTool;
import sk.tuke.kpi.oop.game.tools.Wrench;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {
    private Random random = new Random();
    private boolean oprava= false ;
    public DefectiveLight(){

    }

    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::defLight)).scheduleOn(this);
    }


    private void defLight() {
        if (!oprava) {
            int number = random.nextInt(20);
            if (number == 1) {
                super.toggle();
            }
        }
    }

    @Override
    public boolean repair(BreakableTool tool) {
        if(tool instanceof Wrench){
            tool.useWith(tool);
            oprava = true;
            return true;
        }
        return false;
    }

}
