package monte_carlo_player;

import tablut.TablutBoardState;
import tablut.TablutMove;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class MonteCarloTreeSimulation implements Cloneable
{
    private GameTreeNode root;
    private TablutBoardState board;

    public MonteCarloTreeSimulation(GameTreeNode root, TablutBoardState board)
    {
        this.root = root;
        this.board = board;
    }


    /**
     * Recursively finds the next node to run simulations on
     * @param node the root of the Game Tree
     * @return the node with the greatest UCT value that is a leaf
     */
    public GameTreeNode selectNode(GameTreeNode node)
    {
        if(node.isLeaf())
        {
            return node;
        }

        GameTreeNode maxNode = Collections.max(node.getChildren(), Comparator.comparingDouble(n -> n.getUCT()));

        return selectNode(maxNode);
    }

    /**
     * Gets the board state for a node in the game tree.
     * Applies the moves of each node on the path from the parent to this node to the a clone of the board state
     * @param node a node in the game tree 
     * @return a clone of the board state with the moves applied to it
     */
    public TablutBoardState getSimulationBoard(GameTreeNode node)
    {
        TablutBoardState boardClone = (TablutBoardState) board.clone();
        Stack<TablutMove> moves = new Stack<>();

        GameTreeNode iterNode = node;

        while(iterNode.getParent() != null)
        {
            moves.push(iterNode.getMove());
            iterNode = iterNode.getParent();
        }


        while(!moves.isEmpty())
        {
            boardClone.processMove(moves.pop());
        }

        return boardClone;
    }

    /**
     * Runs a simulation of the Tablut game from a given board state
     * Each player plays a random move until the game is over
     * @param board the current state of the game
     * @return the winner of the simulation
     */
    public int runSimulation(TablutBoardState board)
    {
        TablutBoardState boardClone = (TablutBoardState) board.clone();
        while(!boardClone.gameOver())
        {
            boardClone.processMove((TablutMove) boardClone.getRandomMove());
        }
        return boardClone.getWinner();
    }

    /**
     * Back propagates the results of a simulation.
     * Updates all the nodes to the root.
     * @param node the node from where we run the simulation
     * @param winner the winner of the simulation
     */
    public void backPropagate(GameTreeNode node, int winner)
    {
        GameTreeNode iterNode = node;
        while(iterNode != null)
        {
            iterNode.updateNode(winner);
            iterNode = iterNode.getParent();
        }

    }

    /**
     * Iterates through all the children of the root and selects the one with the greatest win rate
     * @return the move with the greatest win rate
     */
    public TablutMove selectMove()
    {
        GameTreeNode maxNode = Collections.max(root.getChildren(), Comparator.comparingDouble(n -> n.getWinRate()));
        System.out.println("The move selected has a win rate of " + maxNode.getWinRate() + ", and " + maxNode.getSimNum() + "/" + root.getSimNum() + " simulations were ran on this node.");
        System.out.println("The node was selected out of " + root.getChildren().size() + " nodes.");
        return maxNode.getMove();
    }

    /**
     * Iterates through all the children of the root and selects the one with the greatest (win_rate + evaluation_function * 0.01)
     * @param boardState state of the board when the agent needs to select a move
     * @return the move with the greatest (win_rate + evaluation_function * 0.01) value
     */
    public TablutMove selectMove(TablutBoardState boardState)
    {
        GameTreeNode maxNode = null;
        double maxRate = Integer.MIN_VALUE;
        for(GameTreeNode node : root.getChildren())
        {
            TablutBoardState clone = (TablutBoardState) boardState.clone();
            clone.processMove(node.getMove());
            if(node.getWinRate() + (0.01 * clone.evaluateBoard(clone.getTurnPlayer())) > maxRate)
            {
                maxNode = node;
                maxRate = node.getWinRate() + (0.01 * clone.evaluateBoard(boardState.getTurnPlayer()));
            }
        }
        return maxNode.getMove();
    }


    @Override
    public MonteCarloTreeSimulation clone()
    {
        return new MonteCarloTreeSimulation(root, board);
    }



}
