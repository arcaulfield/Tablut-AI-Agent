package alpha_beta_player;

import tablut.TablutBoardState;
import tablut.TablutMove;

import java.util.Collections;
import java.util.List;

public class Minimax {

    static int minimax(int depth, boolean maximizingPlayer, TablutBoardState boardState, int alpha, int beta, int agentPlayer)
    {
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 0 || boardState.gameOver())
            return boardState.evaluateBoard(agentPlayer);

        List<TablutMove> legalMoves = boardState.getAllLegalMoves();
        Collections.shuffle(legalMoves);

        if (maximizingPlayer) {
            int best = Integer.MIN_VALUE;

            for (TablutMove move : legalMoves) {
                TablutBoardState clone = (TablutBoardState) boardState.clone();
                clone.processMove(move);
                int val = minimax(depth - 1, false, clone, alpha, beta, agentPlayer);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                if (beta <= alpha) {
                    break;
                }

            }

            return best;
        }
        else
        {
            int best = Integer.MAX_VALUE;


            for(TablutMove move: legalMoves) {
                TablutBoardState clone = (TablutBoardState) boardState.clone();
                clone.processMove(move);
                int val = minimax(depth - 1, true, clone, alpha, beta, agentPlayer);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                if (beta <= alpha) {
                    break;
                }
            }

            return best;

        }


    }


}
