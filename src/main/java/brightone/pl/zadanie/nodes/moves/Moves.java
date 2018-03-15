package brightone.pl.zadanie.nodes;

import brightone.pl.zadanie.nodes.Figures.AbstrPiece;

/**
 * Created by Lenovo on 2018-03-10.
 */
public class Moves {
    public static void move(Field[][] fields, Coords from, Coords to){
        Field temp = fields[from.getVertical()][from.getHorizontal()];
        fields[from.getVertical()][from.getHorizontal()] = fields[to.getVertical()][to.getHorizontal()];
        fields[to.getVertical()][to.getHorizontal()] = temp;
    }
    public static void move(Field from, Field to){
        Field temp = from;
        from = to;
        to = temp;
    }
    public static void kill(Field from, Field to){
        to = from;
        from = new Field(from.getCoords());
    }

}
