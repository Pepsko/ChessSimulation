package brightone.pl.zadanie.nodes.board;

import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Moves;
import brightone.pl.zadanie.nodes.pieces.Piece;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Board {
    private static int counter=0;
    private static Field[][] fields;
    public Board(Field[][] fields) {
        Board.fields = fields;
    }

    public Field findPieceByID(int id){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                if(id==fields[i][j].getPiece().getId())
                    return fields[i][j];
            }
        }return null;
    }
    public Board() {
        fields = new Field[8][8];
        build();
    }

    private void build(){
        StartSetter.setStartingFields();
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                if(i<4){
                    StartSetter.putPiecesSetColor(Color.BLACK, fields, i, j);
                    counter++;
                }
                else{
                    StartSetter.putPiecesSetColor(Color.WHITE, fields, i, j);
                    counter++;
                }
            }
        }
    }
    public void view(){
        for (int i = 0; i <8; i++) {
            for (int j = 0; j <8 ; j++) {
                System.out.print(fields[i][j]);
            }
            System.out.println();
        }
    }
    public void refresh(Piece piece, Color color){
        Coords move = piece.move(color);
        Moves.move(fields, piece.getField().getCoords(), move);
        piece.getField().setCoords(move);
    }


    public static Field[][] getFields() {
        return fields;
    }

    public static int getCounter() {
        return counter;
    }

    public static void resetCounter(){
        counter=0;
    }

    public static Field getFieldByCoords(Coords coords){
        return Board.fields[coords.getVertical()][coords.getHorizontal()];
    }
    public static Piece getPieceByID(int id){
        for (int i = 0; i <8; i++) {
            for (int j = 0; j <8; j++) {
                if(fields[i][j].getPiece().getId()==id)
                    return fields[i][j].getPiece();
            }
        }
        return null;
    }
}
