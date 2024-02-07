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

/** An interface of methods providing The ability to use alphaBetaPruning search on a 4-in-a-line board.*/
public interface alphaBetaPruningSearchInterface
{
    /**
     * Performs an alpha beta pruning search.
     *
     * @param board Takes in a board object to run a search on
     * @return the best move position 0-64
     */
    int runSearch(fourInALineBoardInterface board);

}
