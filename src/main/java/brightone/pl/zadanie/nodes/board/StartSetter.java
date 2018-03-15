package brightone.pl.zadanie.nodes.Figures;

import brightone.pl.zadanie.nodes.Board;
import brightone.pl.zadanie.nodes.Color;
import brightone.pl.zadanie.nodes.Coords;
import brightone.pl.zadanie.nodes.Field;

/**
 * Created by Lenovo on 2018-03-06.
 */
public class StartSetter {
    public static void setStartingFields(){
        Bishop.setStartingPos();
        Empty.setStartingPos();
        King.setStartingPos();
        Knight.setStartingPos();
        Pawn.setStartingPos();
        Queen.setStartingPos();
        Rook.setStartingPos();
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
