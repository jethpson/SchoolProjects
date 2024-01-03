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

/** An interface of methods providing The ability to perform an A* search.*/
public interface AStarSearchInterface
{
    /**
     * Performs an A* search given a start node and goal Node.
     * The choice of Heuristic is done through a boolean: useManhattanHeuristic
     * In order to show a comparison between the two Heuristic's no matter which the user chooses,
     * The same method can be called again this time with turnOffSolutionPrintForComparisonTest,
     * in order to only show costs and time.
     *
     * @param start The puzzle you wish to solve.
     * @param goal  The goal position you wish to achieve.
     * @param useManhattanHeuristic Determines if the puzzle will be solved with H1 or H2
     * @param turnOffSolutionPrintForComparisonTest allows for reuse of method without reprinting the solution again
     *                                                                                (for comparing h1 and h2)
     */
    void aStarSearch(Node start, Node goal, boolean useManhattanHeuristic, boolean turnOffSolutionPrintForComparisonTest);

    /** Returns a puzzle's Time.
     * @return Returns a puzzle's Time.*/
    long getTime();

    /** Returns a puzzle's cost.
     * @return Returns a puzzle's cost.*/
    int  getCost();

}