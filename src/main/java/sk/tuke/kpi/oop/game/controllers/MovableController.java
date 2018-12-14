package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.framework.actions.Translate;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.*;

public class MovableController implements KeyboardListener {

    private Movable movable;
    private Move move;
    private Set<Direction> currentDir;
    private Map<Input.Key, Direction> keyDirMap = new HashMap<>();

    public MovableController(Movable actor) {
        this.movable = actor;
        this.currentDir = new HashSet<>();
        this.keyDirMap.put(Input.Key.UP, Direction.NORTH);
        this.keyDirMap.put(Input.Key.DOWN, Direction.SOUTH);
        this.keyDirMap.put(Input.Key.RIGHT, Direction.EAST);
        this.keyDirMap.put(Input.Key.LEFT, Direction.WEST);
    }

    @Override
    public void keyPressed(Input.Key key) {
        if (this.keyDirMap.containsKey(key)){
            Direction keyDirection = this.keyDirMap.get(key);
            if (this.move != null){
                this.move.stop();
            }
            if (!this.currentDir.isEmpty()) {
                Direction combinedDirection = Direction.NONE;
                for (Direction d : this.currentDir) {
                    combinedDirection = keyDirection.combine(d);
                }
                this.move = new Move(combinedDirection, Float.MAX_VALUE);
            }
            else {
                this.move = new Move(keyDirection, Float.MAX_VALUE);
            }
            this.currentDir.add(keyDirection);
            if (this.movable.getScene() != null){
                this.movable.getScene().scheduleAction(this.move, this.movable);
            }
        }
    }

    @Override
    public void keyReleased(Input.Key key) {
        if ((this.move != null) && (this.keyDirMap.containsKey(key))){
            this.currentDir.remove(this.keyDirMap.get(key));
            if (!this.currentDir.isEmpty()){
                Direction[] directions = new Direction[this.currentDir.size()];
                this.currentDir.toArray(directions);
                Direction resultDirection = directions[0];
                for (int i = 1; i < directions.length; i++) {
                    if (directions[i] != null)
                        resultDirection = resultDirection.combine(directions[i]);
                }
                this.move.stop();
                this.move = new Move(resultDirection, Float.MAX_VALUE);
                if (this.movable.getScene() != null){
                    this.movable.getScene().scheduleAction(this.move, this.movable);
                }
            }
            else {
                this.move.stop();
            }
        }
    }
}
