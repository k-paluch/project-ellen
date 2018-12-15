package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.behaviours.RunForActor;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Dog;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class map implements SceneListener {
    public static class Factory implements ActorFactory {

        @Nullable
        @Override
        public Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
                System.out.println(name);
                switch (name) {
                    case "ellen":
                        return new Ripley();
                    case "energy":
                        return new Energy();
                    case "back door":
                        return new Door(Door.Orientation.HORIZONTAL);
                    case "front door":
                        return new Door(Door.Orientation.VERTICAL);
                    case "exit door":
                        return new LockedDoor(LockedDoor.Orientation.VERTICAL);
                    case "access card":
                        return new AccessCard();
                    case "ventilator":
                        return new Ventilator();
                    case "locker":
                        return new Locker();
                    case "alien":
                        switch (type) {
                            case "running":
                                return new Alien(
                                    new Observing<>(
                                        World.ACTOR_REMOVED_TOPIC,
                                        actor -> actor instanceof Ammo,
                                        new RunForActor<>()
                                    )
                                );
                            case "waiting1":
                            case "waiting2":
                                return new Alien(
                                    new Observing<>(
                                        Door.DOOR_OPENED,
                                        Door::isOpen,
                                        new RunForActor<>()
                                    )
                                );
                            default:
                                return new Alien(new RandomlyMoving<>());
                        }
                    case "alien mother":
                        return new AlienMother(new RandomlyMoving<>());
                    case "ammo":
                        return new Ammo();
                    case "dog":
                                return new Dog(
                                    new Observing<>(
                                        Door.DOOR_OPENED,
                                        Door::isOpen,
                                        new RunForActor<>()
                                    )
                                );
                }
            }
            return null;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");
        MovableController movableController = new MovableController(ripley);
        CollectorController collectorController = new CollectorController(ripley);
        ShooterController shooterController = new ShooterController(ripley);
        scene.follow(ripley);
        Disposable shooter = scene.getInput().registerListener(shooterController);
        Disposable moves = scene.getInput().registerListener(movableController);
        Disposable collect = scene.getInput().registerListener(collectorController);

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (ripley1 -> {
            shooter.dispose();
            moves.dispose();
            collect.dispose();
        }));
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int topOffset = GameApplication.STATUS_LINE_OFFSET;
        int yTextPos = windowHeight - topOffset;
        Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");
        scene.getGame().pushActorContainer(ripley.getContainer());
        scene.getGame().getOverlay().drawText("| Energy : "+ ripley.getHealth().getValue(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo : "+ ripley.getFirearm().getAmmo(), 260, yTextPos);
    }
}
