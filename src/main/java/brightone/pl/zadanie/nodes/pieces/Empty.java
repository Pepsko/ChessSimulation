package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Empty extends Piece {
    private static final List<Integer> STARTING_POS = new ArrayList<>();
    public Empty(Color color) {
        super(color);
    }

    public static void setStartingPos(){
        for (int i = 1; i <=32; i++) {
            STARTING_POS.add(15+i);
        }
    }

    @Override
    public boolean canMove(Color color) {
        return false;
    }

    @Override
    public boolean canAttack(Color color) {
        return false;
    }

    @Override
    public Direction[] getAllDirections() {
        return new Direction[0];
    }

    @Override
    public List<Field> attackableFields(Color color) {
        return null;
    }

    public Coords move(Color color){
        return null;
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }


    public String getSignature() {
        return "--";
    }

    public static List<Integer> getStartingPos() {
        return STARTING_POS;
    }




}
