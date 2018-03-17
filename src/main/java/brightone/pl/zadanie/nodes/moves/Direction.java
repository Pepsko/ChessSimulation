package brightone.pl.zadanie.nodes.moves;

/**
 * Created by Lenovo on 2018-03-10.
 */
public enum Direction {
    LEFT(0, -1), RIGHT(0,1), DOWN(1,0), UP(-1,0), DOWNLEFT(1,-1), DOWNRIGHT(1,1), UPRIGHT(-1,1), UPLEFT(-1,-1)
    , ALL(8,0), DIAGONAL(4,-4), VERTxHOR(4,0), KNIGHTONE(1,-2), KNIGHTWO(2,-1), KNIGHTTHREE(2,1), KNIGHTFOUR(1,2), KNIGHTFIVE(-1,2), KNIGHTSIX(-2,1),
    KNIGHTSEV(-2,-1), KNIGHTEIGHT(-1,-2);

    private int vertical;
    private int horizontal;

    Direction(int vertical, int horizontal){
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
