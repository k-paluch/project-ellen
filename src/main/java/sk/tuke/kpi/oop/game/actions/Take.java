package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Take<T extends Actor> extends AbstractAction<Keeper<T>> {


    private Class<T> vzatelne;
    public Take(Class<T> vzatelnyactor) {
        this.vzatelne = vzatelnyactor;
        this.setDone(false);
    }

    @Override
        public void execute(float deltaTime){
            Keeper<T> ripley = getActor();
            if(ripley != null) {
                Scene scene = ripley.getScene();
                ActorContainer<T> backpack = ripley.getContainer();
                try {
                    for (Actor item : scene.getActors()) {
                        if (ripley.intersects(item) && vzatelne.isInstance(item) && backpack.getCapacity() != backpack.getSize()) {
                            backpack.add(vzatelne.cast(item));
                            scene.removeActor(item);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Backpack is full");
                }
            }
            setDone(true);
        }
}
