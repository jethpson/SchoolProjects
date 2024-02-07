/* Author: Jacob Thompson
 **
 ** CS4200, winter 2023-2024
 ** Project #3
 ** Due: 1/17/2024
 **
 ** Description:
 ** You have a 8x8 board, players take turns placing a piece on any grid. First player to get 4 in a line (either a
 ** row, or a column; diagonals are NOT counted) wins. The amount of time allowed for generating the next move is 5
 ** seconds. MUST use alpha-beta pruning to determine the computer's move.
 **
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlphaBetaPruningSearch implements alphaBetaPruningSearchInterface
{

    private fourInALineBoardInterface storedBoard;      //Used for the successors method: gets what the player last did.
    private fourInALineBoardInterface successorBoard;   //Used for iteration
    private long startTime;
    private long endTime;
    private int bestBoard;

    private int SEARCH_DEPTH = 8;                       //Targeted SearchDepth

    private int targetRow = 4;                          //The starting position of the successors method sorting.
    private int targetColumn = 4;

    public int runSearch(fourInALineBoardInterface board)
    {

        this.successorBoard = board;
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + 5000;                //5 Seconds max to get the best move.
        this.bestBoard = 0;

        if (storedBoard!=null)
            checkBoardDifference(storedBoard, board);

        maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE, SEARCH_DEPTH);

        storedBoard = copyBoard(board);
        return bestBoard;
    }

    private int maxValue(fourInALineBoardInterface board, int alpha, int beta, int currentDepth)
    {

        if (currentDepth == 0 || board.checkForWin() || board.isFull() || System.currentTimeMillis() >= endTime)
        {
            return board.eval();
        }                                                               //Implementation of the sudo code with
                                                                        //additional eval functions and saving of
        int v = Integer.MIN_VALUE;                                      //best moves.


        List<Integer> successors = generateMoves(board);
        for (int move : successors)
        {

            successorBoard = copyBoard(board);
            successorBoard.newMove(move, 1);
            int result = Math.max(v, minValue(successorBoard, alpha, beta, currentDepth - 1));

            if (result > v)
            {
                                                //Updates the best move.
                v = result;
                bestBoard = move;
            }


            if (beta <= alpha)                  //This is different from the sudo code but without it
            {                                   //everything breaks.

                return v;
            }

            alpha = Math.max(alpha, v);
        }

        return v;
    }

    private int minValue(fourInALineBoardInterface board, int alpha, int beta, int currentDepth)
    {

        if (currentDepth == 0 || board.checkForWin() || board.isFull() || System.currentTimeMillis() >= endTime)
        {

            return board.eval();
        }

        int v = Integer.MAX_VALUE;

        List<Integer> successors = generateMoves(board);
        for (int move : successors)
        {

            successorBoard = copyBoard(board);
            successorBoard.newMove(move, 0);
            int result = Math.min(v, maxValue(successorBoard, alpha, beta, currentDepth - 1));

            if (result < v)
            {

                v = result;
                bestBoard = move;
            }


            if (alpha >= beta)
            {

                return v;
            }

            beta = Math.min(beta, v);
        }

        return v;
    }

    private List<Integer> generateMoves(fourInALineBoardInterface board)
    {
                                                                                      //Generates a successors list
        List<Integer> moves = new ArrayList<>();                                      //that is sorted with proximity
        int[][] currentBoardArray = board.getBoard();                                 //to the last move played by the
                                                                                      //human.
        for (int i = 0; i < 64; i++)
        {

            if (currentBoardArray[0][i] == 0 && currentBoardArray[1][i] == 0)
            {

                moves.add(i);
            }
        }

        moves.sort(Comparator.comparingInt(move -> calculateDistance(move, targetRow, targetColumn)));
        return moves;
    }

    private int calculateDistance(int move, int targetRow, int targetColumn)
    {

        int moveRow = move / 8;
        int moveColumn = move % 8;

        return Math.abs(moveRow - targetRow) + Math.abs(moveColumn - targetColumn);
    }

    private void checkBoardDifference(fourInALineBoardInterface board1, fourInALineBoardInterface board2)
    {

        int[][] boardArray1 = board1.getBoard();
        int[][] boardArray2 = board2.getBoard();
                                                                                        //Determines the last move by
                                                                                        //the human.
        for (int col = 0; col < 64; col++)
        {

            if (boardArray1[0][col] != boardArray2[0][col])
            {

                targetRow = col / 8;
                targetColumn = col % 8;
            }
        }
    }

    private fourInALineBoardInterface copyBoard(fourInALineBoardInterface original)
    {
                                                                                        //Copies the object safety as
        fourInALineBoardInterface copy = new fourInALineBoard();                        //to not override the original
        copy.setBoard(original.getBoard());
        copy.setWinner(original.getWinner());
        return copy;
    }

}