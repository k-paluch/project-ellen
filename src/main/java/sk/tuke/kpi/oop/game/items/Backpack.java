package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {


    private int kapacita;
    private String name;
    private List<Collectible> content;

    public Backpack(String name, int kapacita) {
        this.name = name;
        this.kapacita = kapacita;
        this.content = new ArrayList<>();
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @NotNull
    @Override
    public List<Collectible> getContent() {
        return this.content.subList(0, this.content.size());
    }

    @Override
    public int getCapacity() {
        return this.kapacita;
    }

    @Override
    public int getSize() {
        return this.content.size();
    }

    @Nullable
    @Override
    public Collectible peek() {
        if (this.getSize() > 0){
            return this.content.get(this.getSize() - 1);
        }

        return null;
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        this.content.remove(actor);
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (this.content.size() < this.kapacita){
            this.content.add(actor);
        } else {
            throw new IllegalStateException("Backpack is full, cannot be fullfiled");
        }
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return this.content.iterator();
    }

    @Override
    public void shift() {
        Collections.rotate(this.content, 1);
    }
}
