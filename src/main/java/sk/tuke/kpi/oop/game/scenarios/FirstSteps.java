package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class FirstSteps implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene) {

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int topOffset = GameApplication.STATUS_LINE_OFFSET;
        int yTextPos = windowHeight - topOffset;
        Ripley ripley = (Ripley)scene.getFirstActorByName("Ellen");
        assert ripley != null;
        scene.getGame().pushActorContainer(ripley.getContainer());
        scene.getGame().getOverlay().drawText("| Energy : "+ ripley.getHealth(), 100, yTextPos);
        scene.getGame().getOverlay().drawText("| Ammo : "+ ripley.getAmmo(), 280, yTextPos);
        scene.getGame().getOverlay().drawText("| x: "+ ripley.getPosX(), 320, yTextPos);
        scene.getGame().getOverlay().drawText("| y: "+ ripley.getPosY(), 350, yTextPos);
    }
}
