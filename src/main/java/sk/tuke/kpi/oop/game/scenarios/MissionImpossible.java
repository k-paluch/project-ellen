package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
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

        /*Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");
        MovableController movableController = new MovableController(ripley);
        CollectorController collectorController = new CollectorController(ripley);
        ShooterController shooterController = new ShooterController(ripley);

        scene.getInput().registerListener(shooterController);
        scene.getInput().registerListener(movableController);
        scene.getInput().registerListener(collectorController);

        scene.follow(ripley);
*/
        /*
        scene.getMessageBus().subscribe(LockedDoor.opened_door, door -> {

            if (door.isOpen()){
               Disposable contaminationSpread = new Loop<>(
                    new ActionSequence<>(
                       new Wait<>(1),
                       new Invoke<>(() -> {
                          ripley.setEnergy(ripley.getEnergy() - 1);
                          if (ripley.getEnergy() == 0){
                              ripley.die();
                              collect.dispose();
                              moves.dispose();
                          }
                       })
                   )).scheduleOn(scene);

               scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, ventilator -> {
                   contaminationSpread.dispose();
               });

               scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley1 -> {
                   contaminationSpread.dispose();
               });
           }
        });
        */
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
       /* int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int topOffset = GameApplication.STATUS_LINE_OFFSET;
        int yTextPos = windowHeight - topOffset;

        Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");

        scene.getGame().pushActorContainer(ripley.getContainer());
        scene.getGame().getOverlay().drawText("| Energy : "+ ripley.getHealth().getValue(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo : "+ ripley.getFirearm().getAmmo(), 260, yTextPos);*/
    }
}
