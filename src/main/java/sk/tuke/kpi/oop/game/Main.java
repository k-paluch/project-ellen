package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Game;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.WindowSetup;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.World;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
import sk.tuke.kpi.oop.game.scenarios.customMap;

public class Main {

    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        Game game = new GameApplication(windowSetup);
        Scene scene = new World("world", "maps/custom-map/custom-map.tmx", new customMap.Factory());
        EscapeRoom missionImpossible = new EscapeRoom();
        scene.addListener(missionImpossible);
        game.addScene(scene);
        game.start();

        scene.getInput().onKeyPressed((key) -> {
           if (key == Input.Key.ESCAPE)
               game.stop();
        });
    }
}
