package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.ChainBomb;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.SmartCooler;
import sk.tuke.kpi.oop.game.Teleport;

import java.util.Map;

public class TrainingGameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorArea1 = markers.get("reactor-area-2");
        Reactor reactor = new Reactor();
        scene.addActor(reactor, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();
        MapMarker coolerArea2 = markers.get("cooler-area-2");
        MapMarker coolerArea3 = markers.get("cooler-area-3");
        Teleport t1 = new Teleport(null);
        Teleport t2 = new Teleport(null);
        SmartCooler smartCooler2 = new SmartCooler(reactor);
        SmartCooler smartCooler3 = new SmartCooler(reactor);
        scene.addActor(smartCooler2, coolerArea2.getPosX(), coolerArea2.getPosY());
        scene.addActor(smartCooler3, coolerArea3.getPosX(), coolerArea3.getPosY());
        ChainBomb bomb1 = new ChainBomb(2);
        scene.addActor(bomb1, 100, 100);
        bomb1.activate();
        ChainBomb bomb2 = new ChainBomb(2);
        scene.addActor(bomb2, 100, 150);
        ChainBomb bomb3 = new ChainBomb(2);
        scene.addActor(bomb3, 100, 50);
        ChainBomb bomb4 = new ChainBomb(2);
        scene.addActor(bomb4, 50, 100);
        ChainBomb bomb5 = new ChainBomb(2);
        scene.addActor(bomb5, 50, 100);
        scene.addActor(t1, 40, 200);
        scene.addActor(t2, 270, 200);
        t1.setDestination(t2);
        t2.setDestination(t1);
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(smartCooler2::turnOn),
            new Invoke<>(smartCooler3::turnOn)
        ).scheduleOn(scene);
    }
}
