package brightone.pl.zadanie.nodes;

import brightone.pl.zadanie.nodes.board.Board;
import brightone.pl.zadanie.nodes.board.Field;
import brightone.pl.zadanie.nodes.pieces.Color;
import brightone.pl.zadanie.nodes.moves.AttackInfo;
import brightone.pl.zadanie.nodes.moves.Direction;
import brightone.pl.zadanie.nodes.moves.Moves;
import brightone.pl.zadanie.nodes.pieces.King;

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
            //
            // TimeUnit.SECONDS.sleep(1);
            Round(Color.BLACK);
            board.view();
            System.out.println();
           // TimeUnit.SECONDS.sleep(1);
        }
        board.view();
    }
    private void Round(Color color){
        if(!Moves.attack(color)) {
            board.refresh(Moves.pickPieceToMove(color), color);
        }
    }

    private boolean kingChecked(Color color){
        int id = 4;
        if(color.equals(Color.WHITE)){
            id = 59;
        }
        King king = (King)Board.getPieceByID(id);
        return king.inCheck();
    }
    private boolean checkMate(Color color){
        if(kingChecked(color))
    }

}
