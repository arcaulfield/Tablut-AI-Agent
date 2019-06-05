package alpha_beta_player;

import boardgame.Move;
import tablut.TablutBoardState;
import tablut.TablutMove;
import tablut.TablutPlayer;

/**
 * AI agent playing with a minimax method with alpha beta pruning.
 */
public class AlphaBetaPlayer extends TablutPlayer {

    public AlphaBetaPlayer() {
        super("AlphaBeta Player");
    }


    /**
     * Chooses the best move to play given a board state
     * @param boardState The current board state.
     * @return The move to play
     */
    public Move chooseMove(TablutBoardState boardState) {
        long startTime = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        TablutMove playMove = (TablutMove) boardState.getRandomMove();
        for(TablutMove move: boardState.getAllLegalMoves())
        {
            TablutBoardState clone = (TablutBoardState) boardState.clone();
            clone.processMove(move);
            int val =  Minimax.minimax(2, false, clone, Integer.MIN_VALUE, Integer.MAX_VALUE, boardState.getTurnPlayer());
            if (val > max)
            {
                max = val;
                playMove = move;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Move chosen in " + (endTime - startTime) + "ms.");

        return playMove;

    }


}
