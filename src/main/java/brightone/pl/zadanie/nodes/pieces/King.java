package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class King extends Piece {
    private final String signature = "K";
    private static final List<Integer> STARTING_POS = Arrays.asList(4,59);

    public String getSignature() {
        return signature;
    }

    public Direction[] getPossibleDirections() {
        return new Direction[]{Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT};
    }

    @Override
    public boolean canAttack(Color color) {
        return true;
    }

    public King(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        return (canMoveDiagonally().size()>0)||(canMoveHorizontally().size()>0)||(canMoveVertically().size()>0);
    }

    public static List<Integer> getStartingPos() {
        return STARTING_POS;
    }

    @Override
    public Coords move(Color color) {
        Direction directions;
        do {
            directions = pickDirection(Direction.ALL);
        }while(!checkDirection(directions));
        return  moveInDirection(directions,1);
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyAround(color, directions);
    }

    @Override
    public int getPoints() {
        return 0;
    }
}
