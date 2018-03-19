package brightone.pl.zadanie.nodes;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.moves.AttackInfo;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.moves.Moves;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lenovo on 2018-03-11.
 */
public class Simulation {
    Board board = new Board();
    public void play() throws InterruptedException {
        for (int i = 0; i <100; i++) {
            Round(Color.WHITE);
            board.view();
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
            Round(Color.BLACK);
            board.view();
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
        }
        board.view();
    }
    private void Round(Color color){
        board.refresh(Moves.pickPiece(color), color);
    }

    public AttackInfo attackEnemy(Color color){
        int x=0;
        int check = 0;
        for (int i = 0; i <lookForEnemy(color).size() ; i++) {
            if(lookForEnemy(color).get(i).getVictim().getPiece().getPoints()>x) {
                x = lookForEnemy(color).get(i).getVictim().getPiece().getPoints();
                check=i;
            }
        }return lookForEnemy(color).get(check);
    }

    public Map<Integer,AttackInfo> lookForEnemy(Color color){
        Map<Integer, AttackInfo> possibleAttacks = new HashMap<>();
        int x=0;
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                Field actual = Board.getFields()[i][j];

                if(actual.getPiece().canAttack(color)) {

                    for (int k = 0; k < actual.getPiece().getAllDirections().length; k++) {

                        Direction dir = actual.getPiece().getAllDirections()[k];

                        if (actual.getPiece().attackableField(color, dir) != null) {

                            possibleAttacks.put(x, addAttack(color, actual, dir));
                            x++;
                        }
                    }
                }
            }
        }return possibleAttacks;
    }
    private AttackInfo addAttack(Color color, Field actual, Direction dir){
        return new AttackInfo(actual,actual.getPiece().attackableField(color,dir));
    }
}
