package sk.tuke.kpi.oop.game.actions;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    public PerpetualReactorHeating(float deltaTime){
        execute(1);
    }

    @Override
    public void execute(float deltaTime) {

    }
}
