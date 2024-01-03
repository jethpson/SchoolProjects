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

import java.util.Arrays;
class Node
{

    private final int[][] state;
    int cost;
    Node parent;

    public Node(int[][] state, int cost, Node parent)
    {

        this.state = new int[state.length][];

        for (int i = 0; i < state.length; i++)
        {

            this.state[i] = Arrays.copyOf(state[i], state[i].length);
        }

        this.cost = cost;
        this.parent = parent;
    }

                                        //Overrides for equals and hashCode. Used in java.util.Comparator
    public boolean equals(Object obj)
    {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return Arrays.deepEquals(this.state, other.state);
    }

    public int hashCode() {
        return Arrays.deepHashCode(state);
    }

                                      //Both Search Heuristic methods.
    public int h1()
    {
                                                                //Calculates the number of misplaced tiles
                                                                //in the current state compared to the goal state.
        int misplacedTiles = 0;

        for (int i = 0; i < state.length; i++)                  //Estimates the cost from the current state to the
        {                                                       //goal state based on the number of tiles that are not
                                                                //in their correct positions.
            for (int j = 0; j < state[i].length; j++)
            {

                if (state[i][j] != AStarSearchApp.goalState[i][j] && state[i][j] != 0)
                {

                    misplacedTiles++;
                }
            }
        }

        return misplacedTiles;
    }

    public int h2()
    {

        int manhattanDistance = 0;

        for (int i = 0; i < state.length; i++)                  //Provides an estimate of the cost from the
        {                                                       //current state to the goal state based on the total
                                                                //Manhattan distance of each tile from
            for (int j = 0; j < state[i].length; j++)           //its correct position.
            {

                int value = state[i][j];

                if (value != 0)
                {

                    int goalRow = (value - 1) / 3;
                    int goalCol = (value - 1) % 3;

                    for (int row = 0; row < AStarSearchApp.goalState.length; row++)
                    {

                        for (int col = 0; col < AStarSearchApp.goalState[row].length; col++)
                        {

                            if (AStarSearchApp.goalState[row][col] == value)
                            {

                                goalRow = row;
                                goalCol = col;
                                break;
                            }
                        }
                    }

                    manhattanDistance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }

        return manhattanDistance;
    }
                                                    //Getter methods.
    public int[][] getState() {return state;}
    public int getCost() {return cost;}
    public Node getParent() {return parent;}

}