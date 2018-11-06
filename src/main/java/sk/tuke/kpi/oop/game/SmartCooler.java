package sk.tuke.kpi.oop.game;

public class SmartCooler extends Cooler {
    private int zapnute;
    private boolean is_on;
    public SmartCooler (Reactor reactor){
        super(reactor);
        if(super.getTemperature()>2500){
            turnOn();
        }
        if(super.getTemperature()<1500){
            turnOff();
        }
    }

    @Override
    public void turnOn(){
        zapnute = 1;
    }
    @Override
    public void turnOff(){
        zapnute = 0;
    }
    @Override
    public boolean isOn(){
        if(zapnute==1){
            return true;
        }
        else{
            return false;
        }
    }
}
