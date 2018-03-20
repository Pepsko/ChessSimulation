package brightone.pl.zadanie.nodes.pieces;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public abstract class Piece implements Comparable<Piece>{
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

    public abstract Direction[] getAllDirections();

    public Color getColor(){
        return color;
    }

    public abstract Coords move(Color color);

    public abstract Field attackableField(Color color, Direction directions);

    public abstract Integer getPoints();

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

    public abstract List<Field> attackableFields(Color color);

    public boolean canAttack(Color color){
        return attackableFields(color).size()>0;
    }
    @Override
    public int compareTo(Piece o){
        return getPoints().compareTo(o.getPoints());
    }

    public List<Direction> getPossibleDirections(){
        List<Direction> possibleDirections = new ArrayList<>();
        Coords actualCoords = this.getField().getCoords();
        Coords beingChecked;
        for (int i = 0; i <getAllDirections().length ; i++) {
            beingChecked = actualCoords.addDirection(getAllDirections()[i]);
            if(beingChecked.areFine()){
                possibleDirections.add(getAllDirections()[i]);
            }
        }
        return possibleDirections;
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

    protected Coords chooseRandomMovement(List<Direction> dir){
        Random random = new Random();
        Direction direction = dir.get(random.nextInt(dir.size()));
        int arg = checkMoves(direction);
        while(arg==0){
            direction = dir.get(random.nextInt(dir.size()));
            arg = checkMoves(direction);
        }
        return moveInDirection(direction, random.nextInt(arg)+1);
    }

    protected boolean checkDirection(Direction directions) {
        Coords coords = this.getField().getCoords().addDirection(directions);
        return coords.areFine();
    }

    protected Field findEnemyLongMoves(Color color, Direction directions){
        int vert = directions.getVertical();
        int hor = directions.getHorizontal();
        Coords coords = new Coords(this.getField().getCoords());
        while(coords.add(vert, hor).withinBoard()&& Board.getFieldByCoords(coords.add(vert,hor)).getPiece().getColor()!=color){
            if(Board.getFieldByCoords(coords.add(vert,hor)).getPiece().getColor()==color.getAnother(color))
                return Board.getFieldByCoords(coords.add(vert,hor));
            if(hor>0)hor++;
            if(hor<0)hor--;
            if(vert>0)vert++;
            if(vert<0)vert--;
        }
        return null;
    }
    protected List<Field> canAttackLongMoves(Color color){
        List<Field> attackableFields = new ArrayList<>();
        for (int i = 0; i <getAllDirections().length ; i++) {
            if(findEnemyLongMoves(color, getAllDirections()[i])!=null){
                attackableFields.add(findEnemyLongMoves(color, getAllDirections()[i]));
            }
        }
        return attackableFields;
    }

    protected Field findEnemyAround(Color color, Direction directions){
        Coords coords = new Coords(this.getField().getCoords());
           int hor = directions.getHorizontal();
           int ver = directions.getVertical();
           if(coords.add(ver, hor).withinBoard()&&Board.getFieldByCoords(coords.add(ver, hor)).getPiece().getColor()==color.getAnother(color)) {
               return Board.getFieldByCoords(coords.add(ver, hor));
           } else {
               return null;
           }
    }

    protected List<Field> canAttackAround(Color color){
        List<Field> attackableFields = new ArrayList<>();
        for (int i = 0; i <getAllDirections().length ; i++) {
            if(findEnemyAround(color, getAllDirections()[i])!=null){
                attackableFields.add(findEnemyAround(color, getAllDirections()[i]));
            }
        }
        return attackableFields;
    }

    protected int checkMoves(Direction dir){
        Coords coords = new Coords(this.getField().getCoords());
        int y = dir.getVertical();
        int x = dir.getHorizontal();
        int possibleMoves = 0;
        Coords checkBoard = coords.add(new Coords(y, x));
        while(checkBoard.withinBoard()
                && Board.getFields()[checkBoard.getVertical()][checkBoard.getHorizontal()].isEmpty()){
            if(x>0)x++;
            if(x<0)x--;
            if(y>0)y++;
            if(y<0)y--;
            checkBoard = coords.add(new Coords(y, x));
            possibleMoves++;
        }
        return possibleMoves;
    }

}
/*
    protected List<Direction> canMoveInAllDirections(){
        List<Direction> directions = new ArrayList<>();
        directions.addAll(canMoveDiagonally());
        directions.addAll(canMoveHorizontally());
        directions.addAll(canMoveVertically());
        return directions;
    }
    protected List<Direction> canMoveVertically(){
        List<Direction> directions = new ArrayList<>();
        Coords up = new Coords(this.getField().getCoords()).addDirection(Direction.UP);
        Coords down = new Coords(this.getField().getCoords()).addDirection(Direction.DOWN);
        boolean upCheck = up.withinBoard() && Board.getFields()[up.getVertical()][up.getHorizontal()].isEmpty();
        boolean downCheck = down.withinBoard()&& Board.getFields()[down.getVertical()][down.getHorizontal()].isEmpty();
        if(upCheck) directions.add(Direction.UP);
        if(downCheck) directions.add(Direction.DOWN);
        return directions;
    }
    protected List<Direction> canMoveHorizontally(){
        List<Direction> directions = new ArrayList<>();
        Coords left = new Coords(this.getField().getCoords()).addDirection(Direction.LEFT);
        Coords right = new Coords(this.getField().getCoords()).addDirection(Direction.RIGHT);
        if(left.areFine()) directions.add(Direction.LEFT);
        if (right.areFine()) directions.add(Direction.RIGHT);
        return directions;
    }
    protected List<Direction> canMoveDiagonally(){
        List<Direction> directions = new ArrayList<>();
        Coords leftUp = new Coords(this.getField().getCoords()).addDirection(Direction.UPLEFT);
        Coords rightUp = new Coords(this.getField().getCoords()).addDirection(Direction.UPRIGHT);
        Coords leftDown= new Coords(this.getField().getCoords()).addDirection(Direction.DOWNLEFT);
        Coords rightDown= new Coords(this.getField().getCoords()).addDirection(Direction.DOWNRIGHT);
        boolean leftUpCheck = leftUp.withinBoard() && Board.getFields()[leftUp.getVertical()][leftUp.getHorizontal()].isEmpty();
        boolean rightUpCheck =  rightUp.withinBoard() && Board.getFields()[rightUp.getVertical()][rightUp.getHorizontal()].isEmpty();
        boolean leftDownCheck = leftDown.withinBoard() && Board.getFields()[leftDown.getVertical()][leftDown.getHorizontal()].isEmpty();
        boolean rightDownCheck = rightDown.withinBoard() && Board.getFields()[rightDown.getVertical()][rightDown.getHorizontal()].isEmpty();
        if (leftUpCheck) directions.add(Direction.UPLEFT);
        if(rightUpCheck) directions.add(Direction.UPRIGHT);
        if(leftDownCheck) directions.add(Direction.DOWNLEFT);
        if(rightDownCheck) directions.add(Direction.DOWNRIGHT);
        return directions;
    }
    */

