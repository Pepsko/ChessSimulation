package brightone.pl.zadanie.nodes.board;


import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.pieces.Piece;
import brightone.pl.zadanie.nodes.pieces.Empty;
import brightone.pl.zadanie.nodes.moves.Coords;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Field {
    private static int id=0;
    private String field;
    private Piece piece;
    private boolean empty;
    private Coords coords;

    public Field(Piece piece, Coords coords) {
        empty = false;
        this.piece = piece;
        piece.setField(this);
        this.coords = coords;
        field= piece.getFullSignature();
        piece.setId(id++);
    }

    public Field(Coords coords){
        empty = true;
        Empty empty = new Empty(Color.NONE);
        empty.setField(this);
        this.coords = coords;
        this.piece = empty;
        field=empty.getSignature();
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return field+" ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field1 = (Field) o;

        if (empty != field1.empty) return false;
        if (field != null ? !field.equals(field1.field) : field1.field != null) return false;
        if (piece != null ? !piece.equals(field1.piece) : field1.piece != null) return false;
        return coords != null ? coords.equals(field1.coords) : field1.coords == null;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (piece != null ? piece.hashCode() : 0);
        result = 31 * result + (empty ? 1 : 0);
        result = 31 * result + (coords != null ? coords.hashCode() : 0);
        return result;
    }

    public boolean isEmpty(){
        return empty;
    }


}
