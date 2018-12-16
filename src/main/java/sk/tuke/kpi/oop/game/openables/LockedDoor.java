package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;

public class LockedDoor extends Door {

    private boolean zamknute;

    public LockedDoor(Orientation orientation) {
        super(orientation);
        this.zamknute = false;
    }

    @Override
    public void open() {
        if (!this.isLocked())
            super.open();
    }

    @Override
    public void close() {
        if (!this.isLocked())
            super.close();
    }

    public void lock(){
        this.zamknute = true;
        this.close();
    }

    public void unlock(){
        this.zamknute = false;
        this.open();
    }

    public boolean isLocked() {
        return zamknute;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.lock();
    }
}
