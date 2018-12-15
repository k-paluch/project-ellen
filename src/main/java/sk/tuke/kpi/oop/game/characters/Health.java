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

    public Health(int life, int maxLife) {
        this.maxLife = maxLife;
        this.life = life;
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
        this.life +=amount;
        if(this.life> this.maxLife){
            this.life = this.maxLife;
        }
    }

    public void drain(int amount){
        if(this.life <=0){
            return;
        }
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
