import java.io.*;
import java.net.*;
import java.util.*;

public class client {


	public static void main(String[] args) throws Exception {



		Socket conn = new Socket("localhost", 6666); // 10.110.181.108	//attempts to connect to server
		
		Scanner scnr = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		PrintWriter out = new PrintWriter(conn.getOutputStream());


		System.out.println("Connected!");


		System.out.println("Waiting for confirmation from Server to Start a game of War");
		out.println("hello! would you like to play a game of war?");
		out.flush();
		out.println("Type : yes / no");															//asking the server if they want to play
		out.flush();
		String str = in.readLine();
		System.out.println("server: " + str);


		int Again = 0;																					    //Again is being use to determine if the server wants to play again

		while (Again == 0) {

			int i = 0;
			int Score1 = 26;																				//Each player gets half a deck
			int Score2 = 26;



			while (i < 26) {																				  //prints out the clients deck for the client 

				str = in.readLine();
				System.out.println("server: " + str);

				i++;

			}




			out.println("You go first!");															//doesnt really do anything, just being nice ;p
			out.flush();
			System.out.println("Server is drawing.");


			while (Score1 > 1 && Score2 > 1) {											//statement is redundancy, basically its just a while (true)






				System.out.println("type d to draw a card.");
				String Answer = scnr.nextLine();






				if (Answer.equalsIgnoreCase("d")) {
					out.println("drawed");
					out.flush();
					str = in.readLine();
					System.out.println("server: " + str);
					str = in.readLine();
					System.out.println("server: " + str);

				} else {

					System.out.println("Please just type d to draw a card.");
					continue;

				}

				String Answer2 = in.readLine();

				if (Answer2.equalsIgnoreCase("game")) {                          //game is used to identify the game has concluded
					System.out.println("Game Over!");									//it would be called name untill then
					break;

				}

			}

			str = in.readLine();
			System.out.println("server: " + str);

			System.out.println("Asking the server if they want to play again!");

			str = in.readLine();

			if (str.equalsIgnoreCase("yes")) {                                              //if the server says yes to play again it keeps Again loop going
				Again = 0;																					//otherwise it just exits the program

			} else if (str.equalsIgnoreCase("no")) {
				Again = 1;

				System.exit(1);
			}



		}

		in.close();																							//cloeses everything
		scnr.close();
		out.close();
		conn.close();

	}

}