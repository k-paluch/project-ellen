package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.*;

public class MovableController implements KeyboardListener {

    private Movable movableActor;
    private Move move;
    private Set<Direction> currectDirections;
    private Map<Input.Key, Direction> keyDirectionMap = new HashMap<>();

    public MovableController(Movable actor) {
        this.movableActor = actor;
        this.currectDirections = new HashSet<>();
        this.keyDirectionMap.put(Input.Key.UP, Direction.NORTH);
        this.keyDirectionMap.put(Input.Key.DOWN, Direction.SOUTH);
        this.keyDirectionMap.put(Input.Key.RIGHT, Direction.EAST);
        this.keyDirectionMap.put(Input.Key.LEFT, Direction.WEST);
    }

    @Override
    public void keyPressed(Input.Key key) {
        if (this.keyDirectionMap.containsKey(key)){
            Direction keyDirection = this.keyDirectionMap.get(key);
            if (this.move != null){
                this.move.stop();
            }
            if (!this.currectDirections.isEmpty()) {
                Direction combinedDirection = Direction.NONE;
                for (Direction d : this.currectDirections) {
                    combinedDirection = keyDirection.combine(d);
                }
                this.move = new Move(combinedDirection, Float.MAX_VALUE);
            }
            else {
                this.move = new Move(keyDirection, Float.MAX_VALUE);
            }
            this.currectDirections.add(keyDirection);
            if (this.movableActor.getScene() != null){
                this.movableActor.getScene().scheduleAction(this.move, this.movableActor);
            }
        }
    }

    @Override
    public void keyReleased(Input.Key key) {
        if ((this.move != null) && (this.keyDirectionMap.containsKey(key))){
            this.currectDirections.remove(this.keyDirectionMap.get(key));
            if (!this.currectDirections.isEmpty()){
                Direction[] directions = new Direction[this.currectDirections.size()];
                this.currectDirections.toArray(directions);
                Direction resultDirection = directions[0];
                for (int i = 1; i < directions.length; i++) {
                    if (directions[i] != null)
                        resultDirection = resultDirection.combine(directions[i]);
                }
                this.move.stop();
                this.move = new Move(resultDirection, Float.MAX_VALUE);
                if (this.movableActor.getScene() != null){
                    this.movableActor.getScene().scheduleAction(this.move, this.movableActor);
                }
            }
            else {
                this.move.stop();
            }
        }
    }
}
