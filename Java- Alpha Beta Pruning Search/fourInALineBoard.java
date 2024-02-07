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

import java.util.Arrays;

public class fourInALineBoard implements fourInALineBoardInterface
{

    private int[][] board;
    private int winner;

    public void createAEmtpyBoard()
    {                                   //Creates empty board.

        winner = -1;
        board = new int[2][64];
    }

    public int[][] getBoard()
    {

        return board;
    }

    public boolean newMove(int newPlay, int player)
    {                                   //Plays a move if valid.

        if (board[0][newPlay] != 0) {return false;}
        if (board[1][newPlay] != 0) { return false;}

        board[player][newPlay] = 1;
        return true;
    }

    public void printBoard()
    {                                   //Prints the board.

        char a = 'A';
        System.out.println("  1 2 3 4 5 6 7 8");

        for (int i = 0; i < 8; i++)
        {

            System.out.print(a++ + " ");

            for (int j = 0; j < 8; j++)
            {

                int index = i * 8 + j;

                if (board[0][index] == 1)
                {

                    System.out.print("O ");
                } else if (board[1][index] == 1)
                {

                    System.out.print("X ");
                } else
                {

                    System.out.print("- ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }
    public boolean checkForWin()
    {                                   //Checks for four in a row.
        // Check rows
        for (int i = 0; i < 2; i++)
        {

            for (int row = 0; row < 8; row++)
            {

                for (int col = 0; col < 5; col++)
                {

                    if (board[i][row * 8 + col] == 1 && board[i][row * 8 + col + 1] == 1 &&
                            board[i][row * 8 + col + 2] == 1 && board[i][row * 8 + col + 3] == 1)
                    {

                        if (i==0)
                            winner = 1;
                        else
                            winner = 2;


                        return true;
                    }
                }
            }
        }

        // Check columns
        for (int i = 0; i < 2; i++)
        {

            for (int col = 0; col < 8; col++)
            {

                for (int row = 0; row < 5; row++)
                {

                    if (board[i][row * 8 + col] == 1 && board[i][(row + 1) * 8 + col] == 1 &&
                            board[i][(row + 2) * 8 + col] == 1 && board[i][(row + 3) * 8 + col] == 1)
                    {

                        if(i==0)
                            winner=1;
                        else
                            winner=2;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isFull()
    {                                   //Checks if full.

        for (int i = 0; i < 2; i++)
        {

            for (int j = 0; j < 64; j++)
            {

                if (board[i][j] == 0)
                {

                    return false;
                }
            }
        }

        return true;
    }

    public void setBoard(int[][] Board)
    {                                  //sets the board manually.

        this.board = new int[Board.length][];

        for (int i = 0; i < Board.length; i++)
        {

            this.board[i] = Arrays.copyOf(Board[i], Board[i].length);
        }
    }

    public int eval()
    {
                                                //Evaluates a board based on three things, winning, three in a row and
        checkForWin();                          //two in a row.

        if (winner == 0){return 1000000000;}
        else if (winner == 1){return -1000000000;}
        else if (isFull()){return 0;}

        int total = 0;
        if (checkThree(true) == 1 || checkThree(false) == 1){total -= 20;}
        if (checkThree(true) == -1 || checkThree(false) == -1){total += 20;}
        if (checkTwo(true) == 1 || checkTwo(false) == 1){total -= 40;}
        if (checkTwo(true) == -1 || checkTwo(false) == -1){total += 40;}

        return total;
    }

    private int checkTwo(boolean checkRow)      //Copy of checkWinner method, but with two and three.
    {
        if (checkRow)
        {

        // Check rows
        for (int i = 0; i < 2; i++) {

            for (int row = 0; row < 8; row++) {

                for (int col = 0; col < 3; col++) {

                    if (board[i][row * 8 + col] == 1 && board[i][row * 8 + col + 1] == 1) {

                        if (i == 0)
                            return -1;
                        else
                            return 1;

                    }
                }
            }
        }
    }else {
        // Check columns
        for (int i = 0; i < 2; i++) {

            for (int col = 0; col < 8; col++) {

                for (int row = 0; row < 3; row++) {

                    if (board[i][row * 8 + col] == 1 && board[i][(row + 1) * 8 + col] == 1) {

                        if (i == 0)
                            return -1;
                        else
                            return 1;

                    }
                }
            }
        }
    }
        return 0;
    }

    private int checkThree(boolean checkRow)
    {
        if (checkRow) {

            // Check rows
            for (int i = 0; i < 2; i++) {

                for (int row = 0; row < 8; row++) {

                    for (int col = 0; col < 4; col++) {

                        if (board[i][row * 8 + col] == 1 && board[i][row * 8 + col + 1] == 1 &&
                                board[i][row * 8 + col + 2] == 1) {

                            if (i == 0)
                                return -1;
                            else
                                return 1;

                        }
                    }
                }
            }
        } else {

            // Check columns
            for (int i = 0; i < 2; i++) {

                for (int col = 0; col < 8; col++) {

                    for (int row = 0; row < 4; row++) {

                        if (board[i][row * 8 + col] == 1 && board[i][(row + 1) * 8 + col] == 1 &&
                                board[i][(row + 2) * 8 + col] == 1) {

                            if (i == 0)
                                return -1;
                            else
                                return 1;

                        }
                    }
                }

            }
        }
        return 0;

    }

    //GetterSetters.
    public int getWinner(){return winner;}
    public void setWinner(int winner) {
        this.winner = winner;
    }

}
