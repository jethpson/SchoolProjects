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

/** An interface of methods providing The ability to create and manipulate a four In A Line Board.*/
public interface fourInALineBoardInterface
{
    /** Populates a new object with 0's*/
    void createAEmtpyBoard();

    /** Updates the object with the new move.
     * @return true or false for if the move is valid*/
    boolean newMove(int newPlay, int player);

    /** Searches the object if a player has make 4 connections in a row
     * @return true or false if there is a win, the winner variable to determine who won is accessed by getter.*/
    boolean checkForWin();

    /** Runs an eval function on the current board.
     * @return Returns eval cost.*/
    int eval();

    /** Prints a 8x8 board object*/
    void printBoard();

    /** Returns if the board is full.
     * @return true or false if the board is full*/
    boolean isFull();

    /** Returns a board.
     * @return Returns a board object as an int[2][64].*/
    int[][] getBoard();

    /** Sets a board making sure to not override the current board object(for copying purposes).*/
    void setBoard(int[][] Board);

    /** Gets the winner.
     * @return 0 or 1 for the winner (0=player, 1=computer)*/
    int getWinner();

    /** Sets the winner.(for copying purposes)*/
    void setWinner(int winner);
}
