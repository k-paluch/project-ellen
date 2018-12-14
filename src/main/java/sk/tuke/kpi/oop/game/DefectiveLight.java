package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {

    private Disposable lightBlick;

    public DefectiveLight() {
        super();
    }

    private void simulate(){

        if ((new Random().nextInt(10) + 1) == 1){
            this.toggle();
        }
    }

    private void stopSimulate(){
       if (this.lightBlick != null){
           this.lightBlick.dispose();
           this.lightBlick = null;
       }
    }

    private void startSimulate(){
        this.lightBlick = new Loop<>(new Invoke<>(this::simulate)).scheduleOn(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        this.startSimulate();
    }

    @Override
    public boolean repair() {

        if (this.lightBlick == null){
            return false;
        }

        if (!this.isOn()){
            this.turnOn();
        }

        new ActionSequence<>(
            new Invoke<>(this::stopSimulate),
            new Wait<>(10),
            new Invoke<>(this::startSimulate)
        ).scheduleOn(this);

        return true;
    }
}
