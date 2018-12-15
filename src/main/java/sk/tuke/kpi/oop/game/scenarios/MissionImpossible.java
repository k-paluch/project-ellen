package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {

    public static class Factory implements ActorFactory{

        @Nullable
        @Override
        public Actor create(@Nullable String type, @Nullable String name) {

            if (name != null) {

                switch (name) {
                    case "ellen":
                        return new Ripley();
                    case "energy":
                        return new Energy();
                    case "door":
                        return new LockedDoor(Door.Orientation.VERTICAL);
                    case "access card":
                        return new AccessCard();
                    case "ventilator":
                        return new Ventilator();
                    case "locker":
                        return new Locker();
                }

            }
            return null;
        }
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {


    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {

    }
}
