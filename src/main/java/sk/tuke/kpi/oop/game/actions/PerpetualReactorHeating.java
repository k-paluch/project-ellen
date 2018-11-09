package sk.tuke.kpi.oop.game.actions;
import  org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int increment;
    public PerpetualReactorHeating(int increment) {
        this.increment = increment;
    }


    @Override
    public void execute(float deltaTime) {
        this.getActor().increaseTemperature(increment);
    }


    @NotNull
    @Override
    public Disposable scheduleOn(Reactor actor) {
        return null;
    }

    @NotNull
    @Override
    public Disposable scheduleOn(Scene scene) {
        return null;
    }

}
