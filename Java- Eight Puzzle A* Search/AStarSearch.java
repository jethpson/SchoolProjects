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
 ** including the corresponding search costs (number of nodes generated) and time. The testing program should cover a diverse
 ** range of cases.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch implements AStarSearchInterface
{
    private Node current;
    private int searchCost;                     //Search Cost is the number of nodes generated.
    private long heuristicTime;                 //Heuristic Time is the time it takes for the method to finish.

    public void aStarSearch(Node start, Node goal, boolean useManhattanHeuristic,
                                                   boolean turnOffSolutionPrintForComparisonTest)
    {

        searchCost = 0;
        int stepCount = 0;

        PriorityQueue<Node> priorityQueue;      //Java priorityQueue initialization. It then uses Java's Comparator
                                                //to extract an integer key from the elements being compared
                                                //as a means of prioritization (h(n) + g(n)).

        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost() +
                                                                    (useManhattanHeuristic ? node.h2() : node.h1())));

        Set<Node> traversed = new HashSet<>();          //Hashtable to keep track of traversed paths.


        priorityQueue.add(start);                       //Add the starting puzzle to the queue and start the timer.
        int startTime = (int)System.currentTimeMillis();

        if (!turnOffSolutionPrintForComparisonTest)     //For if the user only wants to see cost and time.
            System.out.println("Solving");

        while (!priorityQueue.isEmpty())
        {

            current = priorityQueue.poll();



            if (current.equals(goal))
            {

                if (!turnOffSolutionPrintForComparisonTest)
                    System.out.println("Solution Found");

                List<Node> path = new ArrayList<>();

                while (current != null)
                {
                                                        //Places the solved puzzle step into a list.
                    path.add(current);
                    current = current.getParent();
                }

                Collections.reverse(path);             //In order to print in the right direction.

                for (Node step : path)
                {

                    if (stepCount == 0)
                    {

                        step.getState();
                        stepCount++;
                    }
                    else
                    {                                   //The reversed list is then printed
                                                        //along with the current step.

                        if (!turnOffSolutionPrintForComparisonTest)
                        {

                            System.out.println("Step: " + stepCount++);
                            printState(step.getState());
                        } else {
                            stepCount++;
                        }
                    }
                }

                int hEndTime = (int)System.currentTimeMillis();
                heuristicTime = hEndTime - startTime;   //End timer and calculate the time it took.

                if (!turnOffSolutionPrintForComparisonTest)
                {

                    System.out.println("H" + ((useManhattanHeuristic ? 1 : 0) + 1) + " Search Cost: " + searchCost);
                } else
                {

                    System.out.println("\nH" + ((!useManhattanHeuristic ? 0 : 1) + 1) +
                                                                                        " Search Cost: " + searchCost);
                }

                if (!turnOffSolutionPrintForComparisonTest)
                {

                    System.out.println("H" + ((useManhattanHeuristic ? 1 : 0) + 1) + " Time: " +
                                                                                      heuristicTime + " milliseconds");
                } else
                {

                    System.out.println("H" + ((!useManhattanHeuristic ? 0 : 1) + 1) + " Time: " +
                                                                                    heuristicTime + " milliseconds\n");
                }


                return;
            }

            traversed.add(current);                     //Adds this step to the traversed table.


            for (Node neighbor : getNeighbors(current)) //Searches the nodes Neighbors and calculates costs.
            {

                if (traversed.contains(neighbor))
                {

                    continue;
                }

                int tentativeCost = current.getCost() + 1;


                if (!priorityQueue.contains(neighbor) || tentativeCost < neighbor.getCost())
                {
                                                        //Checks if the neighbor is not in the priority
                                                        //queue or has a lower tentative cost.
                                                        //Then updates the total cost and adds it to
                    neighbor.cost = tentativeCost;      //the priority queue. <- Where it computes and adds the costs
                    neighbor.parent = current;          //to h1 or h2, into the comparator to be prioritized.

                    if (!priorityQueue.contains(neighbor))
                    {

                        priorityQueue.add(neighbor);                    
                    }
                }
            }
        }

    }

    private List<Node> getNeighbors(Node node)
    {

        List<Node> neighbors = new ArrayList<>();       //This method defines the move set,
                                                        //allowed paths, and generates neighboring nodes.
        int[][] currentState = node.getState();
        int emptyRow = -1;
        int emptyCol = -1;

        for (int i = 0; i < currentState.length; i++)   //Find the position of the empty space in the current state.
        {

            for (int j = 0; j < currentState[i].length; j++)
            {

                if (currentState[i][j] == 0)
                {

                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] move : moves)                        //Iterate through possible moves.
        {

            int newRow = emptyRow + move[0];
            int newCol = emptyCol + move[1];

            if (newRow >= 0 && newRow < currentState.length && newCol >= 0 && newCol < currentState[newRow].length)
            {

                int[][] newState = new int[currentState.length][currentState[0].length];

                for (int i = 0; i < currentState.length; i++)
                {

                    System.arraycopy(currentState[i], 0, newState[i], 0, currentState[i].length);
                }

                newState[emptyRow][emptyCol] = newState[newRow][newCol];
                newState[newRow][newCol] = 0;

                                                        //Swap the values to simulate the move and
                                                        //update the neighbor node.

                Node neighbor = new Node(newState, current.getCost() +1, current);
                neighbors.add(neighbor);
				searchCost++;                           //Creation of new node increases searchCost.
            }
        }

        return neighbors;
    }

    private void printState(int[][] state)
    {
                                                         //Iterate and print a int[][] puzzle.
        for (int[] ints : state)
        {

            for (int anInt : ints)
            {

                    System.out.print(anInt + " ");
            }

                System.out.println();
        }

        System.out.println();
    }
                                                        //Getter methods.
    public long getTime() {return heuristicTime;}
    public int getCost() {return searchCost;}

}

