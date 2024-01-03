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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class AStarSearchApp
{
    public static final int[][] goalState = {{0, 1, 2},           //The Goal State is made public,
                                             {3, 4, 5},           //so it can be seen by all methods.
                                             {6, 7, 8}};
    private static int hundredTestIndex;
    private static Integer[] h1Cost = new Integer[10];
    private static Long[] h1Time = new Long[10];
    private static Integer[] h2Cost = new Integer[10];
    private static Long[] h2Time = new Long[10];


    public static void main(String[] args)
    {

        EightPuzzleInterface puzzle = new EightPuzzle();          //An EightPuzzle object puzzle is created
                                                                  //using EightPuzzleInterface which will be
        boolean exit = false;                                     //reused for all puzzles.
        String prompt;
        String filePath = "allDepthPuzzlesList.txt";
        File file = new File(filePath);

        Scanner scanner = new Scanner(System.in);

        System.out.println("CS 4200 Project 1");
        do
        {

            System.out.println("Select:\n[1] Single Test Puzzle\n[2] Multi-Test Puzzle\n[3] Exit");
            prompt = scanner.nextLine();


            switch (prompt)                                       //Nested switch Statements handle all
            {                                                     //user interaction.

                case "1":

                    System.out.println("Select Input Method:\n[1] Random\n[2] Manual");
                    prompt = scanner.nextLine();

                    switch (prompt) {

                        case "1":

                            puzzle.newRandomPuzzle();                       //Populates the puzzle object with
                                                                            //a random puzzle.
                            while (puzzle.checkInversions())                //It continues to do so until a solvable
                                puzzle.newRandomPuzzle();                   //puzzle is created.

                            puzzle.printPuzzle();                           //Prints the puzzle to the screen.

                            System.out.println("Select H Method:\n[1] H1\n[2] H2");
                            prompt = scanner.nextLine();
                                                                            //Depending on the users choice,
                            if (!prompt.equals("3"))                        //The method will run a search
                                AStarSearchHeuristicsChoice(puzzle, prompt);//on the desired Heuristic.
                            else
                                System.out.println("Invalid selection. Please choose a valid option.");

                            break;

                        case "2":

                            puzzle.newManualPuzzle();                       //The puzzle object is manually
                                                                            //populated by the user.
                            while (puzzle.checkInversions())                //Until a solvable puzzle is provided.
                            {
                                System.out.println("This puzzle can not be solved");
                                puzzle.newManualPuzzle();
                            }

                            puzzle.printPuzzle();

                            System.out.println("Select H Method:\n[1] H1\n[2] H2");
                            prompt = scanner.nextLine();

                            if (!prompt.equals("3"))
                                AStarSearchHeuristicsChoice(puzzle, prompt);
                            else
                                System.out.println("Invalid selection. Please choose a valid option.");

                            break;
                        default:
                            System.out.println("Invalid selection. Please choose a valid option.");

                    }

                    break;

                case "2":

                    int depthPrompt;                    //This case uses a provided depth list txt file to run
                                                        //100 tests and calcuate the average time and cost.
                    do {

                        System.out.println("Please select the Depth which you would like to run 10 tests on " +
                                                       "(even numbers between 2 and 20");
                        while (!scanner.hasNextInt())
                        {

                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine();
                        }

                        depthPrompt = scanner.nextInt();
                    } while (depthPrompt < 2 || depthPrompt > 20 || depthPrompt % 2 != 0);


                    scanner.nextLine();


                    try (BufferedReader reader = new BufferedReader(new FileReader(file)))
                    {

                        String line;

                        for (int k = 2; k<=20; k=k+2)                       //This for loop iterates through every
                        {                                                   //depth until the user selected depth
                                                                            //is recognized, where it is stored as a
                            hundredTestIndex = 0;                           //puzzle and put into the averages arrays.

                            while ((line = reader.readLine()) != null && hundredTestIndex != 10)
                            {

                                if (line.startsWith("Depth"))
                                {

                                    continue;
                                }

                                int[][] puzzles = new int[3][3];

                                int index = 0;

                                for (int i = 0; i < 3; i++)
                                {

                                    for (int j = 0; j < 3; j++)
                                    {

                                            char digitChar = line.charAt(index);
                                            int digit = Character.getNumericValue(digitChar);
                                            puzzles[i][j] = digit;
                                            index++;
                                    }
                                }

                                if (k == depthPrompt)
                                {

                                    puzzle.newManualPuzzle(puzzles);
                                    puzzle.printPuzzle();
                                    AStarSearchHeuristicsChoice(puzzle, "3");
                                }

                                hundredTestIndex++;
                            }
                        }

                        System.out.println("\nTest results averages:\nH1-Times | H2-Times | H1-Costs | H2-Costs");

                        double h1TAverage = calculateAverage(h1Time);
                        double h2TAverage = calculateAverage(h2Time);
                        double h1CAverage = calculateAverage(h1Cost);
                        double h2CAverage = calculateAverage(h2Cost);

                        System.out.println( "  " + h1TAverage + "       " + h2TAverage + "       " + h1CAverage +
                                                                                         "       " + h2CAverage + "\n");

                    } catch (Exception e)
                    {

                        System.out.println("Invalid File Format: Please seperate 10 tests by a line of String Depth");
                    }

                    break;

                case "3":

                    exit = true;

                    break;

                default:
                    System.out.println("Invalid selection. Please choose a valid option.");

            }
        } while (!exit);

        scanner.close();
    }

    private static void AStarSearchHeuristicsChoice(EightPuzzleInterface puzzle, String prompt)
    {

        AStarSearchInterface SolvedPuzzle = new AStarSearch();   //A AStarSearch object is
                                                                 //created and given two nodes,
        Node start = new Node(puzzle.getPuzzle(), 0, null);      //the desired goal state and it's
        Node goal = new Node(goalState, 0, null);                //starting position.

        switch (prompt)
        {                                                    //The aStarSearch method is reusable depending on
                                                             //use case, a start and goal node must be provided, as
            case "1":                                        //well as, the heuristics choice(false = h1, true = h2).
                                                             //Because I am comparing both Heuristics I made the
                                                             //second boolean determine if the solution gets printed.
                                                             //(false = show solutions, true = do not show)

                SolvedPuzzle.aStarSearch(start, goal, false, false);
                SolvedPuzzle.aStarSearch(start, goal, true, true);

                break;

            case "2":

                SolvedPuzzle.aStarSearch(start, goal, true, false);
                SolvedPuzzle.aStarSearch(start, goal, false, true);

                break;

            case "3":                                       //This case is used for the 100 test cases, it
                                                            //helps populates an Interger/Long array.

                SolvedPuzzle.aStarSearch(start, goal, false, true);
                h1Cost[hundredTestIndex] = SolvedPuzzle.getCost();
                h1Time[hundredTestIndex] = SolvedPuzzle.getTime();
                SolvedPuzzle.aStarSearch(start, goal, true, true);
                h2Cost[hundredTestIndex] = SolvedPuzzle.getCost();
                h2Time[hundredTestIndex] = SolvedPuzzle.getTime();
                break;

            default:
                System.out.println("Invalid selection. Please choose a valid option.");
        }
    }

    private static <T extends Number> double calculateAverage(T[] array)
    {

        if (array.length == 0)
        {

            return 0;
        }                                                   //Java Generics method for calculating
                                                            //the averages of java numbers types as arrays,
        double sum = 0;                                     //and returns the results as a double.
        for (T number : array)
        {

            sum += number.doubleValue();
        }

        return sum / array.length;
    }

}