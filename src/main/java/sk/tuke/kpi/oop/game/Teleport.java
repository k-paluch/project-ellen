package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    private Teleport blink;
    private boolean maybe;
    private Player ppl;
    private Animation tele = new Animation("sprites/lift.png", 48, 48, 0);

    public Teleport() {
        setAnimation(tele);

    }

    @Override
    public void addedToScene(Scene scene) {
        new Loop<>(new Invoke(this::blink)).scheduleOn(this);
    }

    private void blink() {
        int x = getPosX();

        int y = getPosY();

        int w = getWidth();

        int h = getHeight();

        int playerY = ppl.getPosY() + ppl.getHeight() / 2;

        int playerX = ppl.getPosX() + ppl.getWidth() / 2;

        if (!(x < playerX && x + w > playerX) || !(y < playerY && y + h > playerY))
            maybe = true;
        else if (maybe && blink != null) {
            teleportPlayer(ppl);
            blink.maybe = false;
        }
    }

    public Teleport getDestination() {
        return blink;
    }

    public void setDestination(Teleport destinationTeleport) {
        blink = destinationTeleport;
    }

    public void teleportPlayer(Player player) {
        ppl.setPosition(blink.getPosX() + 8, blink.getPosY() + 8);
    }
}
