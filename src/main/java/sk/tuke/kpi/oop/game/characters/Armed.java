package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.SuperFirearm;

public interface Armed<Gun> extends Actor {
    Firearm getFirearm();
    SuperFirearm getSuperFirearm();
    void setFirearm(Firearm weapon);
    void setSuperFirearm(SuperFirearm weapon);
}
