package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromAngle(float angle){

        switch ((int) angle){
            case 0:
                return NORTH;
            case 315:
                return NORTHEAST;
            case 270:
                return EAST;
            case 225:
                return SOUTHEAST;
            case 180:
                return SOUTH;
            case 135:
                return SOUTHWEST;
            case 90:
                return WEST;
            case 45:
                return NORTHWEST;
            default:
                return NORTH;
        }
    }


    public Direction combine(Direction other){
        int cX ;
        int cY ;

        if((this.getDy()==other.getDy())&&(this.getDx()==other.getDx())){
            return other;
        }

        if((this.getDy()==other.getDy())&&(this.getDx()!=other.getDx())){
            cY=this.getDy();
            cX=(this.getDx()+other.getDx())/2;
            for (Direction direction : Direction.values()){
                if ((direction.getDx() == cX) && (direction.getDy() == cY)){
                    return direction;
                }
            }
        }

        if((this.getDy()!=other.getDy())&&(this.getDx()==other.getDx())){
            cX=this.getDx();
            cY=(this.getDy()+other.getDy())/2;
            for (Direction direction : Direction.values()){
                if ((direction.getDx() == cX) && (direction.getDy() == cY)){
                    return direction;
                }
            }
        }

        /*if ((cX > -2) && (cX < 2) && (cY > -2) && (cY < 2)){
            for (Direction direction : Direction.values()){
                if ((direction.getDx() == cX) && (direction.getDy() == cY)){
                    return direction;
                }
            }
        }*/

        return Direction.NONE;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle(){

        switch (this){
            case NORTH:
                return 0;
            case NORTHEAST:
                return 315;
            case EAST:
                return 270;
            case SOUTHEAST:
                return 225;
            case SOUTH:
                return 180;
            case SOUTHWEST:
                return 135;
            case WEST:
                return 90;
            case NORTHWEST:
                return 45;
            default:
                return 0;
        }
    }
}
