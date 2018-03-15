package brightone.pl.zadanie.nodes.figures;

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
        return false;
    }

    public Direction[] getPossibleDirections() {
        return new Direction[]{Direction.KNIGHTONE, Direction.KNIGHTWO,
                Direction.KNIGHTTHREE, Direction.KNIGHTFOUR, Direction.KNIGHTSEV, Direction.KNIGHTEIGHT};
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
        Random random = new Random();
        Coords coords = new Coords(this.getField().getCoords());
        int vertical=1;
        int horizontal=2;
        Coords change;
        do {
            switch (random.nextInt(2)) {
                case 0:
                    vertical = 1;
                    horizontal = 2;
                    break;
                case 1:
                    vertical = 2;
                    horizontal = 1;
            }
            switch (random.nextInt(4)) {
                case 0:
                    vertical = -vertical;
                    break;
                case 1:
                    horizontal = -horizontal;
                    break;
                case 2:
                    vertical = -vertical;
                    horizontal = -horizontal;
                    break;
                case 3:
                    vertical = vertical;
                    horizontal = horizontal;
                    break;
            }
            change = new Coords(vertical, horizontal);
            if(this.movePossible(change))
                coords.add(change);
        }while(coords.equals(this.getField().getCoords()));
        return coords;
    }

    @Override
    public Field attackableField(Color color, Direction directions) {
        return findEnemyDiagonally(color, directions);
    }

}
