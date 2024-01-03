/* Author: Jacob Thompson
 **
 ** CS4200, winter 2023-2024
 ** Project #1
 ** Due: 1/3/2024
 **
 ** Description:
 ** Implementing the A* search algorithm to solve the 8-puzzle problem.
 ** generating random 8-puzzle problems with varying solution lengths (2 to 20 steps) or using provided inputs.
 ** The objective is to collect data on different solution lengths,
 ** including the corresponding search costs (number of nodes generated). The testing program should cover a diverse
 ** range of cases.
 */

/** An interface of methods providing basic operations for the creation of Eight Puzzle problem questions.
 * Either through random or manual input.*/
public interface EightPuzzleInterface
{
    /** Creates a new puzzle as a int[][].*/
    void newRandomPuzzle();
	
    /** Creates a new puzzle based upon user input.*/
    void newManualPuzzle();
    
	/** Creates a new puzzle based upon a given int[][] puzzle.*/
    void newManualPuzzle(int[][] puzzles);
    
	/** Prints a Puzzle.*/
    void printPuzzle();
    
	/** Checks if a puzzle is solvable.
     *  @return False for Solvable and True for not Solvable.*/
    boolean checkInversions();
    
	/** Returns a puzzle.
     * @return a puzzle as a int[][].*/
    int[][] getPuzzle();


}
