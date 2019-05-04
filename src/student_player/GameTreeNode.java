package student_player;

import boardgame.Move;
import tablut.TablutMove;

import java.util.ArrayList;

public class GameTreeNode
{
    private int simNum;
    private int winNum;
    private TablutMove move;
    private ArrayList<GameTreeNode> children;
    private GameTreeNode parent;


    public int getSimNum()
    {
        return simNum;
    }

    public int getWinNum()
    {
        return winNum;
    }

    public TablutMove getMove()
    {
        return move;
    }

    public ArrayList<GameTreeNode> getChildren()
    {
        return children;
    }
    public boolean isLeaf()
    {
        return children.isEmpty();
    }

    public GameTreeNode getParent()
    {
        return parent;
    }

    public GameTreeNode(GameTreeNode parent, TablutMove move)
    {
        this.move = move;
        this.parent = parent;
        children = new ArrayList<>();
        simNum = 0;
        winNum = 0;

    }

    /**
     * Adds children to the node.
     * Each child has a possible move that could be made from the this game state.
     * @param possibleMoves the possible moves from this game state
     */
    public void expandNode(ArrayList<TablutMove> possibleMoves)
    {
        for(TablutMove move : possibleMoves)
        {
            children.add(new GameTreeNode(this, move));
        }

    }

    /**
     * @return the first child of this GameTreeNode
     */
    public GameTreeNode getChild()
    {
        return children.get(0);
    }

    /**
     * Checks to see if the current node is the player that won
     * Adds 2 to the number of wins if the current node is the winner, 0 if the opponent won and 1 if it's a tie.
     * @param winPlayer the ID number of the player that just won
     */
    public void updateNode(int winPlayer) {

        simNum += 2;

        if(move.getPlayerID() == winPlayer)
        {
            winNum += 2;
        }
        else if(parent.move.getPlayerID() == winPlayer)
        {
            return;
        }
        else
        {
            winNum += 1;
        }
    }

    /**
     * Calculates and returns the UCT value of this node
     * @return the upper confidence tree value
     */
    public double getUCT()
    {
        if (simNum == 0)
        {
            return Double.MAX_VALUE;
        }
        return winNum + 1.41 * Math.sqrt((double) parent.simNum / simNum);
    }



}
