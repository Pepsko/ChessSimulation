package brightone.pl.zadanie.nodes.moves;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.pieces.Piece;

import java.util.*;

/**
 * Created by Lenovo on 2018-03-10.
 */
public class Moves {
    public static void move(Field[][] fields, Coords from, Coords to){
        Field temp = fields[from.getVertical()][from.getHorizontal()];
        fields[from.getVertical()][from.getHorizontal()] = fields[to.getVertical()][to.getHorizontal()];
        fields[to.getVertical()][to.getHorizontal()] = temp;
    }
    public static Piece pickPieceToMove(Color color){
        List<Integer> toCheck = new ArrayList<>();
        for (int i = 0; i <16; i++) {
            toCheck.add(i);
        }
        while (true) {
            Random random = new Random();
            Collections.shuffle(toCheck);
            Integer pick = toCheck.get(random.nextInt(toCheck.size()));
            if (color == Color.WHITE) {
                pick = pick + 16;
            }
            if (Board.getPieceByID(pick).canMove(color)) {
                return Board.getPieceByID(pick);
            }else{
                if (color==Color.WHITE){
                    pick = pick-16;
                }
                toCheck.remove(pick);
            }
        }
    }
    public static Piece pickPieceToAttack(Color color){
        for (Map.Entry<Piece, List<Field>> entry : mapOfPossibleAttacks(color).entrySet()){
            Piece key = entry.getKey();
            List<Field> value = entry.getValue();
        }
    }
    private static Map<Piece, List<Field>> mapOfPossibleAttacks(Color color){
        Map<Piece, List<Field>> pieceAndPossibleAttacks = new HashMap<>();
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8; j++) {
                if(Board.getFields()[i][j].getPiece().canAttack(color)){
                    Piece actualPiece = Board.getFields()[i][j].getPiece();
                    pieceAndPossibleAttacks.put(actualPiece, actualPiece.attackableFields(color));
                }
            }
        }
        return pieceAndPossibleAttacks;
    }

}
