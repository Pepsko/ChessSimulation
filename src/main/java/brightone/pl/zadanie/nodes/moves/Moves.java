package brightone.pl.zadanie.nodes.moves;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.pieces.Piece;

import java.util.Random;

/**
 * Created by Lenovo on 2018-03-10.
 */
public class Moves {
    public static void move(Field[][] fields, Coords from, Coords to){
        Field temp = fields[from.getVertical()][from.getHorizontal()];
        fields[from.getVertical()][from.getHorizontal()] = fields[to.getVertical()][to.getHorizontal()];
        fields[to.getVertical()][to.getHorizontal()] = temp;
    }
    public static Piece pickPiece(Color color){
        while (true) {
            Random random = new Random();
            int pick = random.nextInt(16);
            if (color == Color.WHITE) {
                pick = pick + 16;
            }
            if (Board.getPieceByID(pick).canMove(color)) {
                return Board.getPieceByID(pick);
            }
        }
    }

}
