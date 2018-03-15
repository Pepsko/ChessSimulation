package brightone.pl.zadanie.nodes.figures;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.moves.Coords;
import brightone.pl.zadanie.nodes.moves.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2018-03-05.
 */
public class Pawn extends Piece {
    private final int points = 1;
    private final String signature = "p";
    private static final List<Integer> STARTING_POS = new ArrayList<>();

    public static void setStartingPos(){
        for (int i = 1; i <9; i++) {
            STARTING_POS.add(7+i);
            STARTING_POS.add(47+i);
        }
    }

    public String getSignature() {
        return signature;
    }

    public Direction[] getPossibleDirections() {
        return new Direction[]{Direction.DOWN, Direction.UP};
    }

    public Pawn(Color color) {
        super(color);
        super.setFullSignature(signature+color.getSignature());
    }

    @Override
    public boolean canMove(Color color) {
        int x = 1;
        int ver = this.getField().getCoords().getVertical();
        int hor = this.getField().getCoords().getHorizontal();
        if(color==Color.WHITE)
            x=-1;
        return new Coords(ver + x, hor).areFine() && Board.getFields()[ver + x][hor].isEmpty();
    }

    public static List<Integer> getStartingPos(){
        return STARTING_POS;
    }
    public int getPoints() {
        return points;
    }



    @Override
    public Coords move(Color color) {
        Random rand = new Random();
        Coords coords = new Coords(this.getField().getCoords());
        int shift = rand.nextInt(2)+1;
        if(color==Color.WHITE){
            shift=-shift;
        }
        if((shift==2||shift==-2) && Board.getFields()[coords.getVertical()][coords.getHorizontal()].isEmpty()){
            coords.setVertical(coords.getVertical()+shift);
        }
        else
            coords.setVertical(coords.getVertical()+shift);
        return coords;
    }
    @Override
    public boolean canAttack(Color color){
        int vert = this.getField().getCoords().getVertical();
        int hor = this.getField().getCoords().getHorizontal();
        int y=1;
        int x=1;
        if(color==Color.WHITE) y=-1;
        Coords leftOne = new Coords(vert + y, hor - x);
        Coords rightOne = new Coords(vert + y, hor + x);
        Color leftColor=Color.NONE;
        Color rightColor=Color.NONE;
        if(leftOne.areFine())
        leftColor = Board.getFieldByCoords(leftOne).getPiece().getColor();
        if(rightOne.areFine())
        rightColor = Board.getFieldByCoords(rightOne).getPiece().getColor();
        boolean left = leftOne.areFine() && leftColor != color && leftColor != Color.NONE;
        boolean right = rightOne.areFine() && rightColor != color && rightColor != Color.NONE;
        return left||right;
    }
    @Override
    public Field attackableField(Color color, Direction dirs){
        int vert = this.getField().getCoords().getVertical();
        int hor = this.getField().getCoords().getHorizontal();
        int y=1;
        int x=1;
        if(color==Color.WHITE) y=-1;
        Coords leftOne = new Coords(vert + y, hor - x);
        Coords rightOne = new Coords(vert + y, hor + x);
        Color leftColor=Color.NONE;
        Color rightColor=Color.NONE;
        if(leftOne.areFine())
        leftColor = Board.getFieldByCoords(leftOne).getPiece().getColor();
        if(rightOne.areFine())
        rightColor = Board.getFieldByCoords(rightOne).getPiece().getColor();
        boolean left = leftOne.areFine() && leftColor != color && leftColor != Color.NONE;
        boolean right = rightOne.areFine() && rightColor != color && rightColor != Color.NONE;
        if(left) return Board.getFieldByCoords(leftOne);
        else if(right) return Board.getFieldByCoords(rightOne);
        return null;
    }
}
