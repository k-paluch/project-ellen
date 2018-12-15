package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.actions.SuperFire;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class ShooterController implements KeyboardListener {
    private Ripley armed;

    public ShooterController(Ripley actor) {
        this.armed = actor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        switch (key){
            case P:
                new SuperFire<>().scheduleOn(this.armed);

                case SPACE:
                new Fire<>().scheduleOn(this.armed);
            default:
        }
    }
}
