package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Game;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.WindowSetup;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.World;
import sk.tuke.kpi.oop.game.scenarios.map;

public class Main {

    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 1028, 720);

        Game game = new GameApplication(windowSetup);
        Scene scene = new World("world", "maps/map.tmx", new map.Factory());
        map custommap = new map();
        scene.addListener(custommap);
        game.addScene(scene);
        game.start();

        scene.getInput().onKeyPressed((key) -> {
           if (key == Input.Key.ESCAPE)
               game.stop();
        });
    }
}
