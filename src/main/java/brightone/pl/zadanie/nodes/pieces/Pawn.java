package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Pawn extends Piece {
    private final int points = 1;
    private final String signature = "p";
    private static final List<Integer> STARTING_POS = new ArrayList<>();

    public static void setStartingPos(){
        for (int i = 1; i <9; i++) {
            STARTING_POS.add(7+i);
            STARTING_POS.add(47+i);
        }
    }

    public String getSignature() {
        return signature;
    }

    public Direction[] getAllDirections() {
        return new Direction[]{Direction.DOWN, Direction.UP};
    }

    public Pawn(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        int x = 1;
        int ver = this.getField().getCoords().getVertical();
        int hor = this.getField().getCoords().getHorizontal();
        if(color==Color.WHITE)
            x=-1;
        return new Coords(ver + x, hor).withinBoard() && Board.getFields()[ver + x][hor].isEmpty();
    }

    public static List<Integer> getStartingPos(){
        return STARTING_POS;
    }

    public int getPoints() {
        return points;
    }



    @Override
    public Coords move(Color color) {
        Random rand = new Random();
        Coords coords = new Coords(this.getField().getCoords());
        int shift = rand.nextInt(2)+1;
        if(color.equals(Color.WHITE)){
            shift=-shift;
        }
        if((shift==2||shift==-2) && Board.getFields()[coords.getVertical()][coords.getHorizontal()].isEmpty()){
            coords.setVertical(coords.getVertical()+shift);
        }
        else
            coords.setVertical(coords.getVertical()+shift);
        return coords;
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return null;
    }

    @Override
    public List<Field> canAttack(Color color){
       Direction directions[] = {Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT};
       int x = 2;
       if(color.equals(Color.BLACK)){
           x= x+2;
       }
       List<Field> attackableFields = new ArrayList<>();
        for (int i = x-2; i <x; i++) {
            if(findEnemyAround(color, directions[i])!=null) {
                attackableFields.add(findEnemyAround(color, directions[i]));
            }
        }
        return attackableFields;
    }
}
