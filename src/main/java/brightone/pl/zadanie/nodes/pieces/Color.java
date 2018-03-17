package brightone.pl.zadanie.nodes.pieces;

/**
 * Created by Lenovo on 2018-03-05.
 */
public enum Color {
    BLACK("B"), WHITE("W"), NONE("-");

    private String signature;

    Color(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public Color getAnother(Color color){
        if (color==WHITE)return BLACK;
        else if(color==BLACK)return WHITE;
        else return NONE;
    }
}
