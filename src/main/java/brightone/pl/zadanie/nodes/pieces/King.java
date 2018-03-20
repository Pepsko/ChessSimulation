package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class King extends Piece {
    private final String signature = "K";
    private static final List<Integer> STARTING_POS = Arrays.asList(4,59);

    public String getSignature() {
        return signature;
    }

    public Direction[] getAllDirections() {
        return new Direction[]{Direction.UPLEFT, Direction.UPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT};
    }

    @Override
    public List<Field> attackableFields(Color color) {
        return canAttackAround(color);
    }

    public King(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        return getPossibleDirections().size()>0;
    }

    public static List<Integer> getStartingPos() {
        return STARTING_POS;
    }

    @Override
    public Coords move(Color color) {
        Direction direction;
        Random random = new Random();
        do {
            direction = getPossibleDirections().get(random.nextInt(getPossibleDirections().size()));
        }while(!checkDirection(direction));
        return  moveInDirection(direction,1);
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyAround(color, directions);
    }

    @Override
    public Integer getPoints() {
        return 0;
    }
}
