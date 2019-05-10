package monte_carlo_player;

import boardgame.Move;
import tablut.TablutBoardState;
import tablut.TablutPlayer;

/**
 * AI agent playing with a monte carlo tree search method.
 */
public class MonteCarloPlayer extends TablutPlayer {

    public MonteCarloPlayer() {
        super("MonteCarlo Player");
    }

    /**
     * Chooses the best move to play given a board state
     * @param boardState The current board state.
     * @return The move to play
     */
    public Move chooseMove(TablutBoardState boardState) {

        long startTime = System.currentTimeMillis();

        GameTreeNode root = new GameTreeNode(null, null);
        MonteCarloTreeSimulation simulation = new MonteCarloTreeSimulation(root, boardState);

        long currentTime = System.currentTimeMillis();

        Move myMove = boardState.getRandomMove();
        int winner;
        GameTreeNode simNode;
        GameTreeNode childNode;
        TablutBoardState simulationBoardState;

        while(currentTime - startTime < 1800)
        {
            simNode = simulation.selectNode(root);
            simulationBoardState = simulation.getSimulationBoard(simNode);
            if(!simulationBoardState.gameOver())
            {
                simNode.expandNode(simulationBoardState.getAllLegalMoves());
                childNode = simNode.getRandomChild();
                simulationBoardState.processMove(childNode.getMove());
            }
            else
            {
                childNode = simNode;
            }
            winner = simulation.runSimulation(simulationBoardState);
            simulation.backPropagate(childNode, winner);
            currentTime = System.currentTimeMillis();
        }

        System.out.println(root.getSimNum() + " simulations were ran in 1800ms");

        if(!root.getChildren().isEmpty())
        {
            myMove = simulation.selectMove();

        }

        // Return your move to be processed by the server.

        return myMove;
    }
}