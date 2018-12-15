package sk.tuke.kpi.oop.game.characters;

import java.util.HashSet;
import java.util.Set;

public class Health {

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    private Set<ExhaustionEffect> exhaustionEffects;
    private int maxLife;
    private int life;

    public Health(int maxLife) {
        this.maxLife = maxLife;
        this.life = maxLife;
        this.exhaustionEffects = new HashSet<>();
    }

    public Health(int maxLife, int life) {
        this.maxLife = maxLife;
        this.life = Math.max(maxLife,life);
        this.exhaustionEffects = new HashSet<>();
    }

    private void applyObservers(){
       for (ExhaustionEffect exhausted: this.exhaustionEffects){
           exhausted.apply();
       }
    }

    public void restore(){
        this.life = this.maxLife;
    }

    public int getValue() {
        return life;
    }

    public void refill(int amount){
        this.life = Math.max(this.maxLife,this.life+amount);
    }

    public void drain(int amount){
        this.life -= Math.abs(amount);
        if (this.life < 0){
            this.life = 0;
        }
        if (this.life == 0){
            this.applyObservers();
        }
    }

    public void onExhaustion(ExhaustionEffect effect){
        this.exhaustionEffects.add(effect);
    }

    public void exhaust() {
        this.life = 0;
        this.applyObservers();
    }
}
