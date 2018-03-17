package brightone.pl.zadanie.nodes.board;

import brightone.pl.zadanie.nodes.pieces.*;
import brightone.pl.zadanie.nodes.moves.Coords;

/**
 * Created by Lenovo on 2018-03-06.
 */
public class StartSetter {
    public static void setStartingFields(){
        Empty.setStartingPos();
        Pawn.setStartingPos();
    }
    public static void putPiecesSetColor(Color color, Field fields[][], int i, int j){
        Coords coords = new Coords(i, j);
        if(Pawn.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new Pawn(color), coords);
        }

        if(Rook.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new Rook(color), coords);
        }

        if(Knight.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new Knight(color), coords);
        }

        if(Bishop.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new Bishop(color), coords);
        }

        if(Queen.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new Queen(color), coords);
        }

        if(King.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(new King(color), coords);
        }
        if(Empty.getStartingPos().contains(Board.getCounter())) {
            fields[i][j] = new Field(coords);
        }
    }

}
