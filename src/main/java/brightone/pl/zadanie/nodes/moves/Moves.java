package brightone.pl.zadanie.nodes.moves;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.pieces.Empty;
import brightone.pl.zadanie.nodes.pieces.Piece;

import java.util.*;

/**
 * Created by Lenovo on 2018-03-10.
 */
public class Moves {
    private static List<Integer> blackPiecesLeft = fillListOfIDs(Color.BLACK);
    private static List<Integer> whitePiecesLeft = fillListOfIDs(Color.WHITE);

    private static List<Integer> fillListOfIDs(Color color){
        List<Integer> list = new ArrayList<>();
        Integer temp = 16;
        if(color.equals(Color.WHITE)){
            temp+=16;
        }
        for (int i = temp-16; i <temp; i++) {
            list.add(i);
        }
        return list;
    }
    private static void removeBeatenPiece(Color color, Piece attackedPiece){
        if (color.equals(Color.WHITE)){
            blackPiecesLeft.remove(attackedPiece.getId());
        }else {
            whitePiecesLeft.remove(attackedPiece.getId());
        }
    }
    public static void move(Field[][] fields, Coords from, Coords to){
        Field temp = fields[from.getVertical()][from.getHorizontal()];
        fields[from.getVertical()][from.getHorizontal()] = fields[to.getVertical()][to.getHorizontal()];
        fields[to.getVertical()][to.getHorizontal()] = temp;
    }
    public static void attack(Field[][] fields, Piece from, Field to){
        Coords attacker = from.getField().getCoords();
        Coords victim = to.getCoords();
        fields[victim.getVertical()][victim.getHorizontal()] = fields[attacker.getVertical()][attacker.getHorizontal()];
        fields[attacker.getVertical()][attacker.getHorizontal()] = new Field(attacker);
    }
    public static Piece pickPieceToMove(Color color){
        List<Integer> toCheck = fillListOfIDs(color);
        while (true) {
            Collections.shuffle(toCheck);
            Integer pick = toCheck.get(0);
            if (Board.getPieceByID(pick).canMove(color)) {
                return Board.getPieceByID(pick);
            }else{
                toCheck.remove(pick);
            }
        }
    }

    public static Piece pickPieceToAttack(Color color){
        Piece pieceWithBestAttack = null;
        int points=0;
        if (!mapOfBestPossibleAttacks(color).isEmpty()) {
            for (Map.Entry<Piece, Field> entry : mapOfBestPossibleAttacks(color).entrySet()) {
                if (entry.getValue().getPiece().getPoints() > points) {
                    pieceWithBestAttack = entry.getKey();
                }
            }
        }
        return pieceWithBestAttack;
    }

    private static Map<Piece, Field> mapOfBestPossibleAttacks(Color color){
        List<Integer> piecesLeft;
        if(color.equals(Color.BLACK)){
            piecesLeft = blackPiecesLeft;
        }else{
            piecesLeft = whitePiecesLeft;
        }
        Map<Piece, Field> pieceAndPossibleAttacks = new HashMap<>();
        for (int actualId: piecesLeft) {
            Piece actual = Board.getPieceByID(actualId);
            if(chooseBiggestFromList(actual.attackableFields(color))!=null)
            pieceAndPossibleAttacks.put(actual, chooseBiggestFromList(actual.attackableFields(color)));
        }
        return pieceAndPossibleAttacks;
    }

    private static Field chooseBiggestFromList(List<Field> fields){
        Field temp=null;
        if(fields!=null && !fields.isEmpty()) {
            for (Field field : fields) {
                int points = 0;
                if (field.getPiece().getPoints() > points) {
                    temp = field;
                    points = field.getPiece().getPoints();
                }
            }
        }
        return temp;
    }

    public static boolean attack(Color color){
        Piece attackingPiece=null;
        if(pickPieceToAttack(color)!=null) {
            attackingPiece = pickPieceToAttack(color);
        }
        Field attackedField = mapOfBestPossibleAttacks(color).get(attackingPiece);
        if(attackingPiece!=null) {
            attack(Board.getFields(), attackingPiece, attackedField);
            removeBeatenPiece(color, attackedField.getPiece());
            return true;
        }
        return false;
    }

}
