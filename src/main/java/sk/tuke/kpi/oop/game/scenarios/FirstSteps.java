package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene) {


        /*
        Ripley ripley = new Ripley();
        ripley.setEnergy(50);
        scene.addActor(ripley, 0,0);

        Energy energy = new Energy();
        scene.addActor(energy, 40,40);

        Ammo ammo = new Ammo();
        scene.addActor(ammo, 0,40);

        Wrench wrench = new Wrench();
        scene.addActor(wrench, 30,80);

        FireExtinguisher fireExtinguisher = new FireExtinguisher();
        scene.addActor(fireExtinguisher, 30,200);

        scene.scheduleAction(new Take<>(Collectible.class), ripley);
        */
        /*
        new When<>(
            action -> energy.intersects(ripley),
            new Use<>(energy)
        ).scheduleOn(ripley);


        new When<>(
            action -> ammo.intersects(ripley),
            new Use<>(ammo)
        ).scheduleOn(ripley);
        */

        /*
        MovableController movableController = new MovableController(ripley);
        CollectorController collectorController = new CollectorController(ripley);

        scene.getInput().registerListener(movableController);
        scene.getInput().registerListener(collectorController);
        */
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int topOffset = GameApplication.STATUS_LINE_OFFSET;
        int yTextPos = windowHeight - topOffset;
        Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");
        scene.getGame().pushActorContainer(ripley.getContainer());
        scene.getGame().getOverlay().drawText("| Energy : "+ ripley.getHealth(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo : "+ ripley.getAmmo(), 280, yTextPos);
        scene.getGame().getOverlay().drawText("| x: "+ ripley.getPosX(), 320, yTextPos);
        scene.getGame().getOverlay().drawText("| y: "+ ripley.getPosY(), 350, yTextPos);
    }
}
