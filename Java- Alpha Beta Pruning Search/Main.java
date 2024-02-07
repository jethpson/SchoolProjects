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

import java.util.Scanner;

public class Main
{

    public static String prompt;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {

        fourInALineBoardInterface board = new fourInALineBoard();                       //Creates both the board object
        alphaBetaPruningSearchInterface alphaBetaSearch = new AlphaBetaPruningSearch(); //and the search object.

        boolean exit = false;

        System.out.println("CS 4200 Project 3:\n");
        do
        {

            System.out.println("Would you like to go first? (y/n): ");
            board.createAEmtpyBoard();
            prompt = scanner.nextLine();

            switch (prompt.toLowerCase())                                               //Switch statements handle all
            {                                                                           //user interaction.

                case "y"://Player chooses their position first.

                    board.printBoard();
                    while (true)
                    {

                        while (!board.newMove(PlayerChooseMove(), 0))
                            System.out.println("Invalid position: Please try again.");

                        board.printBoard();                             //Prints the board.
                        if(board.checkForWin())                         //Checks for a win after every play.
                        {                                               //for the rest of this project 1 = human and
                            if(board.getWinner() == 1)                  //0 = computer in winning contexts.
                                System.out.println("Player Wins");
                            else
                                System.out.println("Computer Wins");

                            break;
                        }

                        System.out.println();

                        int best = alphaBetaSearch.runSearch(board);    //Runs the alphabetaSearch and returns a board
                        board.newMove(best, 1);                   //position 0-64

                        board.printBoard();

                        if(board.checkForWin())
                        {
                            if(board.getWinner() == 1)
                                System.out.println("Player Wins");
                            else
                                System.out.println("Computer Wins");

                            break;
                        }
                    }

                    break;

                case "n"://For the computer going first, just opposite of the above.

                    board.printBoard();
                    while (true)
                    {

                        int best = alphaBetaSearch.runSearch(board);
                        board.newMove(best, 1);
                        board.printBoard();
                        if(board.checkForWin())
                        {
                            if(board.getWinner() == 1)
                                System.out.println("Player Wins");
                            else
                                System.out.println("Computer Wins");

                            break;
                        }

                        System.out.println();

                        while (!board.newMove(PlayerChooseMove(), 0))
                            System.out.println("Invalid position: Please try again.");

                        board.printBoard();
                        if(board.checkForWin())
                        {
                            if(board.getWinner() == 1)
                                System.out.println("Player Wins");
                            else
                                System.out.println("Computer Wins");

                            break;
                        }

                    }

                    break;

                default:
                    System.out.println("Invalid input: Please try again.");
            }

            System.out.println("Would you like to play again? (y/n):");
            prompt = scanner.nextLine();

            switch (prompt.toLowerCase())
            {

                case "y":
                    exit = false;
                    break;
                case "n":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid input: Please try again.");
            }

        } while (!exit);

        scanner.close();
    }

    private static int PlayerChooseMove()                       //A method for asking and determining if a players
    {                                                           //desired move is valid, the method, newMove in the
                                                                //board object is what checks if it has already been
        while (true)                                            //played.
        {

            try
            {

                System.out.print("Choose your next move: ");

                prompt = scanner.nextLine().toUpperCase();


                char column = prompt.charAt(0);

                if (column <= 'H')
                {

                    int row = Integer.parseInt(prompt.substring(1));
                    int position = (column - 'A') * 8 + (row - 1);

                    if (position >=0 && position <=64 && row <= 8)
                        return position;
                    else
                        System.out.println("Invalid input: Please try Again.");
                } else
                    System.out.println("Invalid input: Please try Again.");

            }catch (Exception e)
            {

                System.out.println("Invalid input: Please try Again.");
            }
        }
    }

}
