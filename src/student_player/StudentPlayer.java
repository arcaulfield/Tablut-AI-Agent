package student_player;

import boardgame.Move;
import tablut.TablutBoardState;
import tablut.TablutPlayer;

/** A player file submitted by a student. */
public class StudentPlayer extends TablutPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("Goose Player");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
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

        while(currentTime - startTime < 1800)
        {
            simNode = simulation.selectNode(root);
            simNode.expandNode((simulation.getSimulationBoard(simNode)).getAllLegalMoves());
            childNode = simNode.getChild();
            winner = simulation.runSimulation(simulation.getSimulationBoard(childNode));
            simulation.backPropagate(childNode, winner);
            currentTime = System.currentTimeMillis();
        }

        if(!root.getChildren().isEmpty())
        {
            myMove = simulation.selectMove();

        }

        // Return your move to be processed by the server.

        return myMove;
    }
}