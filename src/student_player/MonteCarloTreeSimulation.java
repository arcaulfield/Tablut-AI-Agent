package student_player;

import tablut.TablutBoardState;
import tablut.TablutMove;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class MonteCarloTreeSimulation
{
    private GameTreeNode root;
    private TablutBoardState board;

    /**
     * Recursively finds the next node to run simulations on
     * @param node the root of the Game Tree
     * @return the node with the greatest UCT value that is a leaf
     */
    public GameTreeNode selectNode(GameTreeNode node)
    {

        double currentMax = Double.MIN_VALUE;

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
            moves.push(node.getMove());
            iterNode = iterNode.getParent();
        }


        while(!moves.isEmpty())
        {
            boardClone.processMove(moves.pop());
        }

        return boardClone;
    }

}
