package brightone.pl.zadanie.nodes.moves;

/**
 * Created by Lenovo on 2018-03-07.
 */
public class Coords {
    private int vertical;
    private int horizontal;

    public Coords(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Coords() {
    }

    public Coords(Coords coords){
        this.vertical = coords.getVertical();
        this.horizontal = coords.getHorizontal();
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public boolean equalsVertically(Coords coords){
        return this.vertical==coords.vertical;
    }

    public boolean equalsHorizontally(Coords coords){
        return this.horizontal==coords.horizontal;
    }

    public Coords add(Coords coords){
        return new Coords(this.vertical + coords.getVertical(), this.horizontal + coords.getHorizontal());
    }

    public Coords add(int vert, int hor){
        return new Coords(this.vertical+vert, this.horizontal+hor);
    }

    public void setCoords(int vertical, int horizontal){
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords coords = (Coords) o;

        if (vertical != coords.vertical) return false;
        return horizontal == coords.horizontal;
    }

    @Override
    public int hashCode() {
        int result = vertical;
        result = 31 * result + horizontal;
        return result;
    }

    @Override
    public String toString() {
        return vertical +" "+ horizontal;
    }

    public boolean areFine(){
        return !(horizontal < 0 || horizontal > 7 || vertical < 0 || vertical > 7);
    }
}
