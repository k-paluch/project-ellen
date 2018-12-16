package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.SuperFirearm;

public interface SuperArmed extends Actor {
    SuperFirearm getSuperFirearm();
    void setSuperFirearm(Firearm weapon);
}
