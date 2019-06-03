# Tablut AI Agent

AI agent for Tablut, a deterministic board game with no hidden information 

<img src="https://github.com/arcaulfield/Calculator/blob/master/BaseCalculatorDemo.png" width="450"/>

## The Game
Tablut is a variant of the classic Viking board game Hnefatafl. The game consists of two players, the Swedes (white) and the Muscovites (black), and is played on a 9-by-9 board. The Muscovites, who start with 16 pieces, play first. Their goal is to capture the swedish king. On the other hand, the Swedes start with 9 pieces. They fight to protect their king. In order to do this, they must move him to one of the four corners of the board. The swedish king starts in the centre of the board and is the piece that is marked with a "K". The player that accomplishes their goal first wins. The players tie if 40 moves have been played and neither player has won. The game ends when either a player has won, or the move limit has been reached. 
On their turn, each player moves one of their pieces. All pieces can move to any square that is either directly horizontal or vertical from their current position, with the exception of the centre square and the four corner squares. Only the king can move to these squares.
A piece is captured if, as a result of an opponent's move, it is "sandwiched" between two opponent pieces, or it is "sandwiched" between the an opponent piece and either a corner square or the centre piece. When the king is in the centre square or in a position that neighbours the centre, it requires extra protection and must be completely surrounded by four opponent pieces to be captured. A captured piece is removed from the board. 
## The Agent's Strategy
The AI agent uses a minimax algorithm with alpha beta pruning, in order to determine its optimal move. To find the utility of a player at each node of the minimax game tree, an evaluation function is used. This determines how beneficial a board state is for the player. The evaluation function is a function of:
* whether or not a player had one, or if the players tied
* the amount of the agent player's pieces on the board 
* the amount of the opponent player's pieces on the board 
* the proximity of the king to the corner
* the number of opponent pieces neighbouring the king 
</ br/>
Give it a try and see if you can beat it! 
