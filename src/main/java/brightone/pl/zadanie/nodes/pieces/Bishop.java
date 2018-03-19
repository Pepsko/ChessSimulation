package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Bishop extends Piece {
    private final int points = 3;
    private final String signature = "b";
    private static final List<Integer> STARTING_POS = Arrays.asList(2,5,61,58);

    public String getSignature() {
        return signature;
    }

    public Bishop(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }
    @Override
    public boolean canMove(Color color) {
        return canMoveDiagonally().size()>0;
    }

    @Override
    public boolean canAttack(Color color) {
        return true;
    }

    @Override
    public Coords move(Color color) {
        return chooseRandomMovement(canMoveDiagonally());
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        if(findEnemyDiagonally(color,directions)!=null)
            return findEnemyDiagonally(color, directions);
        else return null;
    }

    public int getPoints() {
        return points;
    }


    public static List<Integer> getStartingPos() {
        return STARTING_POS;
    }
    public Direction[] getAllDirections() {
        return new Direction[]{Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT};
    }
}
