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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EightPuzzle implements EightPuzzleInterface
{
    private int[][] puzzle;
	
    public void newRandomPuzzle()
    {

        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Collections.shuffle(numbers);

        puzzle = new int[3][3];                         //With the help of Java's collections import
        int index = 0;                                  //shuffling an array is easy.

        for (int i = 0; i < puzzle.length; i++)         //This shuffled array is fed into a 2D array.
        {

            for (int j = 0; j < puzzle[i].length; j++)
            {

                puzzle[i][j] = numbers.get(index++);
            }
        }

    }

    public void newManualPuzzle()
    {

        puzzle = new int[3][3];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the puzzle manually (use a space to separate " +
                                    "numbers and hit enter after three numbers):");
        boolean tryAgain;

        do                                              //As long as the user's inputted values are
        {                                               //numbers, they will be added as a puzzle.
                                                        //The inversion Check still needs to be made.
            tryAgain = false;
            for (int i = 0; i < puzzle.length && !tryAgain; i++)
            {

                for (int j = 0; j < puzzle[i].length && !tryAgain; j++)
                {

                    try
                    {

                        puzzle[i][j] = scanner.nextInt();
                    } catch (Exception e)
                    {

                        System.out.println("Invalid input. Please input a number.");
                        scanner.nextLine();
                        tryAgain = true;
                    }
                }
            }
        } while (tryAgain);

    }

    public void newManualPuzzle(int[][] puzzles)
    {

        puzzle = puzzles;
    }

    public boolean checkInversions()
    {
                                                        //This method checks both the inversions which
        int inversions = 0;                             //occur in the provided puzzle, and the validity
        int currentValue;                               //of the puzzle in general(0-8 and no repeating numbers).

        Set<Integer> seenNumbers = new HashSet<>();     //A hashTable is used to quickly detect seen
                                                        //numbers.
        for (int i = 0; i < puzzle.length; i++)
        {

            for (int j = 0; j < puzzle[i].length; j++)
            {

                currentValue = puzzle[i][j];

                if (currentValue < 0 || currentValue > 8)
                {

                    System.out.println("Invalid input. Numbers should be in the range [0, 8].");
                    return true;
                }


                if (!seenNumbers.add(currentValue))     //Returns false if you add the same number twice.
                {

                    System.out.println("Invalid input. Numbers should not repeat.");
                    return true;
                }

                if (currentValue != 0)                  //Whenever a tile with a greater number on it precedes a tile
                {                                       //with a smaller number the Inversion Count increases.

                    for (int m = i; m < puzzle.length; m++)
                    {

                        int N = (m == i) ? j + 1 : 0;

                        for (int n = N; n < puzzle[m].length; n++)
                        {

                            if (puzzle[m][n] != 0 && puzzle[m][n] < currentValue)
                            {

                                inversions++;
                            }
                        }
                    }
                }
            }
        }

        return inversions % 2 != 0;                     //Returns if the Inversion count is even (solvable).
    }

    public void printPuzzle()
    {
                                                        //Prints the int[][] puzzle.
        System.out.println("\nPuzzle:");

        for (int[] row : puzzle)
        {

            for (int value : row)
            {

                System.out.print(value + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public int[][] getPuzzle() {return puzzle;}			//Getter for the puzzle objects contents.

}
