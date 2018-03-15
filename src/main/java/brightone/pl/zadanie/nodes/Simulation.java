package brightone.pl.zadanie.nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
      //  if(attackEnemy(color)!=null)
       //     Moves.kill(attackEnemy(color).getAttacker(), attackEnemy(color).getVictim());
        Random random1 = new Random();
        Random random2= new Random();
        Coords coords = new Coords(random1.nextInt(8), random2.nextInt(8));
        Field move= Board.getFieldByCoords(coords);
        do {
            if(coords.areFine()&& Board.getFieldByCoords(coords).isEmpty())
            move = Board.getFieldByCoords(coords);
            else coords=new Coords(random1.nextInt(8), random2.nextInt(8));
            if(move.getPiece().canMove(color))
            board.refresh(move, color);
        }while(move.getPiece().getColor()!=color && !move.getPiece().canMove(color));
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

                if(actual.getPiece().canAttack(color))

                    for (int k = 0; k <actual.getPiece().getPossibleDirections().length; k++) {

                    Directions dir = actual.getPiece().getPossibleDirections()[k];

                        if(actual.getPiece().attackableField(color, dir)!=null) {

                            possibleAttacks.put(x, addAttack(color, actual, dir));
                            x++;
                        }
                    }
            }
        }return possibleAttacks;
    }
    private AttackInfo addAttack(Color color, Field actual, Directions dir){
        AttackInfo attack = new AttackInfo(actual,actual.getPiece().attackableField(color,dir));
        return attack;
    }
}
