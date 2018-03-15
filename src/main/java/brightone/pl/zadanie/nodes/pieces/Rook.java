package brightone.pl.zadanie.nodes.figures;

import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.board.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Rook extends Piece {
    private final int points = 4;
    private final String signature = "r";
    private static final List<Integer> STARTING_POS = Arrays.asList(0, 7, 56, 63);

    public Rook(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        return canMoveHorizontally()||canMoveVertically();
    }

    public Direction[] getPossibleDirections() {
        return new Direction[]{Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT};
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
        return chooseRandomMovement(Direction.VERTxHOR);
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyDiagonally(color, directions);
    }

}
