package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Knight extends Piece {
    private final int points = 2;
    private final String signature = "k";
    private static final List<Integer> STARTING_POS = Arrays.asList(1,6,57,62);

    public Knight(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        return getPossibleDirections().size()>0;
    }
    @Override
    public Direction[] getAllDirections() {
        return new Direction[]{Direction.KNIGHTONE, Direction.KNIGHTWO,
                Direction.KNIGHTTHREE, Direction.KNIGHTFOUR,Direction.KNIGHTFIVE, Direction.KNIGHTSIX,
                Direction.KNIGHTSEV, Direction.KNIGHTEIGHT};
    }

    @Override
    public List<Field> canAttack(Color color) {
        List<Field> attackableFields = new ArrayList<>();
        for (int i = 0; i <getAllDirections().length; i++) {
            if(findEnemyAround(color, getAllDirections()[i])!=null) {
                attackableFields.add(findEnemyAround(color, getAllDirections()[i]));
            }
        }
        return attackableFields;
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
       Random random = new Random();
       Coords actualCoords = this.getField().getCoords();
       List<Direction> directions = getPossibleDirections();
       return actualCoords.addDirection(directions.get(random.nextInt(directions.size())));
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyLongMoves(color, directions);
    }

}
