package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;

public class LockedDoor extends Door {

    private boolean locked;

    public LockedDoor(Orientation orientation) {
        super(orientation);
        this.locked = false;
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
        this.locked = true;
        this.close();
    }

    public void unlock(){
        this.locked = false;
        this.open();
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.lock();
    }
}
