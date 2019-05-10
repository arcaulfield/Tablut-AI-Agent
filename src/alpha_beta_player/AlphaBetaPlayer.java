package alpha_beta_player;

import boardgame.Move;
import tablut.TablutBoardState;
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
        return boardState.getRandomMove();
    }


}
