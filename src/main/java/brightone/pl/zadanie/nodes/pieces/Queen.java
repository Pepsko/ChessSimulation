package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Queen extends Piece {
    private final int points = 5;
    private final String signature = "h";
    private static final List<Integer> STARTING_POS = Arrays.asList(3, 60);

    public Queen(Color color ) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        return canMoveDiagonally().size()>0||canMoveHorizontally().size()>0||canMoveVertically().size()>0;
    }

    public Direction[] getAllDirections() {
        return new Direction[]{Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT};
    }

    @Override
    public boolean canAttack(Color color) {
        return true;
    }

    public String getSignature() {
        return signature;
    }

    public static List<Integer> getStartingPos() {
        return STARTING_POS;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public Coords move(Color color) {
        return chooseRandomMovement(canMoveInAllDirections());
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyDiagonally(color, directions);
    }

    public boolean canMove(){
        return canMoveDiagonally().size()>0||canMoveHorizontally().size()>0||canMoveVertically().size()>0;
    }

}
