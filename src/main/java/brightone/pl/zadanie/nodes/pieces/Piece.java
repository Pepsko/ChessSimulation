package brightone.pl.zadanie.nodes.figures;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;

import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public abstract class Piece {
    private Color color;
    private String fullSignature;
    private Field field;
    private int id;
    public String getFullSignature() {
        return fullSignature;
    }

    public void setFullSignature(String fullSignature) {
        this.fullSignature = fullSignature;
    }
    public abstract Direction[] getPossibleDirections();
    public Color getColor(){
        return color;
    }
    public abstract boolean canAttack(Color color);
    public abstract Coords move(Color color);
    public abstract Field attackableField(Color color, Direction directions);
    public abstract int getPoints();
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Piece(Color color) {
        this.color = color;
    }
    public abstract boolean canMove(Color color);

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }
    protected boolean movePossible(Coords move) {
        Coords check = new Coords(this.getField().getCoords());
        check = check.add(move);
        return check.areFine() && Board.getFields()[check.getVertical()][check.getHorizontal()].isEmpty();
    }
    protected boolean canMoveVertically(){
        Coords up = new Coords(this.getField().getCoords()).add(new Coords(1, 0));
        Coords down = new Coords(this.getField().getCoords()).add(new Coords(-1, 0));
        boolean upCheck = up.areFine() && Board.getFields()[up.getVertical()][up.getHorizontal()].isEmpty();
        boolean downCheck = down.areFine()&& Board.getFields()[down.getVertical()][down.getHorizontal()].isEmpty();
        return upCheck||downCheck;
    }
    protected boolean canMoveHorizontally(){
        Coords left = new Coords(this.getField().getCoords()).add(new Coords(0, 1));
        Coords right = new Coords(this.getField().getCoords()).add(new Coords(0, -1));
        boolean leftCheck = left.areFine() && Board.getFields()[left.getVertical()][left.getHorizontal()].isEmpty();
        boolean rightCheck =  left.areFine() && Board.getFields()[left.getVertical()][left.getHorizontal()].isEmpty();
        return leftCheck||rightCheck;
    }
    protected boolean canMoveDiagonally(){
        Coords leftUp = new Coords(this.getField().getCoords()).add(new Coords(-1, -1));
        Coords rightUp = new Coords(this.getField().getCoords()).add(new Coords(-1, 1));
        Coords leftDown= new Coords(this.getField().getCoords()).add(new Coords(1, -1));
        Coords rightDown= new Coords(this.getField().getCoords()).add(new Coords(1, 1));
        boolean leftUpCheck = leftUp.areFine() && Board.getFields()[leftUp.getVertical()][leftUp.getHorizontal()].isEmpty();
        boolean rightUpCheck =  rightUp.areFine() && Board.getFields()[rightUp.getVertical()][rightUp.getHorizontal()].isEmpty();
        boolean leftDownCheck = leftDown.areFine() && Board.getFields()[leftDown.getVertical()][leftDown.getHorizontal()].isEmpty();
        boolean rightDownCheck = rightDown.areFine() && Board.getFields()[rightDown.getVertical()][rightDown.getHorizontal()].isEmpty();
        return leftUpCheck||rightUpCheck||leftDownCheck||rightDownCheck;
    }
    protected int checkMoves(Direction dir){
        Coords coords = new Coords(this.getField().getCoords());
        Coords checkBoard = new Coords(this.getField().getCoords());
        int y = dir.getVertical();
        int x = dir.getHorizontal();
        int possibleMoves = 0;
        while(coords.add(new Coords(y, x)).areFine()
                && Board.getFields()[checkBoard.getVertical()+y][checkBoard.getHorizontal()+x].isEmpty()){
            if(x>0)x++;
            if(x<0)x--;
            if(y>0)y++;
            if(y<0)y--;
            possibleMoves++;
        }
        return possibleMoves;
    }
    protected Coords moveInDirection(Direction dir, int moves){
        Coords coords = new Coords(this.getField().getCoords());
        int y = dir.getVertical();
        int x = dir.getHorizontal();
        for (int i = 0; i <moves-1; i++) {
            if(x>0)x++;
            if(x<0)x--;
            if(y>0)y++;
            if(y<0)y--;
        }
        coords.setCoords(coords.getVertical()+y,coords.getHorizontal()+x);
        return coords;
    }
    protected Coords chooseRandomMovement(Direction dir){
        int arg = 0;
        Direction directions = pickDirection(dir);
        while(arg==0){
            directions = pickDirection(dir);
            arg = this.checkMoves(directions);
        }
        Random random = new Random();
        return moveInDirection(directions, random.nextInt(arg)+1);
    }
    protected Direction pickDirection(Direction dir){
        Random random = new Random();
        switch (random.nextInt(dir.getVertical())-dir.getHorizontal()){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            case 4:
                return Direction.DOWNLEFT;
            case 5:
                return Direction.DOWNRIGHT;
            case 6:
                return Direction.UPLEFT;
            case 7:
                return Direction.UPRIGHT;
        }
        return Direction.DOWN;
    }
    protected boolean checkDirection(Direction directions) {
        int vert = this.getField().getCoords().getVertical() + directions.getVertical();
        int hor = this.getField().getCoords().getHorizontal() + directions.getHorizontal();
        Coords coords = new Coords(vert, hor);
        return coords.areFine() && Board.getFields()[vert][hor].isEmpty();
    }
    protected Field findEnemyDiagonally(Color color, Direction directions){
        int vert = directions.getVertical();
        int hor = directions.getHorizontal();
        Coords coords = new Coords(this.getField().getCoords());
        while(coords.add(vert, hor).areFine()&& Board.getFieldByCoords(coords.add(vert,hor)).getPiece().getColor()!=color){
            if(Board.getFieldByCoords(coords.add(vert,hor)).getPiece().getColor()==color.getAnother(color))
                return Board.getFieldByCoords(coords.add(vert,hor));
            if(hor>0)hor++;
            if(hor<0)hor--;
            if(vert>0)vert++;
            if(vert<0)vert--;
        }
        return null;
    }
    protected Field findEnemyAround(Color color, Direction directions){
        Coords coords = new Coords(this.getField().getCoords());
           int hor = directions.getHorizontal();
           int ver = directions.getVertical();
           if(coords.add(ver, hor).areFine()&&Board.getFieldByCoords(coords.add(ver, hor)).getPiece().getColor()==color.getAnother(color))
               return Board.getFieldByCoords(coords.add(ver, hor));
           else return null;
        }

}

