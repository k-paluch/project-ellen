package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.oop.game.weapons.Firearm;

public interface Armed {
    Firearm getFirearm();
    void setFirearm(Firearm weapon);
}
