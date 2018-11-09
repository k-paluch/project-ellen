package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;

public class SmartCooler extends Cooler {
    private int zapnute;
    public SmartCooler (Reactor reactor){
        super(reactor);
    }

    @Override
    public void addedToScene(Scene scene) {
        if(getCoolerReactor() !=null){
            if (getCoolerReactor().getTemperature() < 1500)
                turnOff();
            if (getCoolerReactor().getTemperature() > 2500)
                turnOn();
        }
        super.addedToScene(scene);
    }

}
