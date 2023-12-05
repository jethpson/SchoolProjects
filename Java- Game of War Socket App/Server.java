import java.io.*;
import java.net.*;
import java.util.*;


public class Server{
    public static void main(String[] args) throws IOException {
    
    	

        ServerSocket server = new ServerSocket(6666);   //opens socket
        

        System.out.println("waiting for client");
        Socket conn = server.accept();                    //connects clients socket
        System.out.println("connected!");

        Scanner scnr = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        DataOutputStream intout = new DataOutputStream(conn.getOutputStream());
        
        
        String str = in.readLine();                       //Client asks server if it wants to play,
        System.out.println("client: "+str);				  //I was learning how it worked so thats why it's
               str = in.readLine();                       //so unnecessary
        System.out.println("client: "+str);
        
        int Again = 0;									  // Again determines if the player wants to play again
       
        
    	
    	
   while (true) {                                         //asks the server yes or no; to start game
        
	   
	   String Answer = scnr.nextLine();
       
	   
	   if (Answer.equalsIgnoreCase("yes")){
     
            out.println("Ok! Here's your randomized deck.");
            out.flush();
            
            System.out.println("Ok! Here's your randomized deck.");
            
            break;

        } else if (Answer.equalsIgnoreCase("no")){

            out.println("The server declined your request : disconnecting");
            out.flush();
            System.out.println(";0 : disconnecting");

            in.close();
            out.close();
            conn.close();
            System.exit(1);

        } else {
            out.println("Waiting for Server to Decide");
            out.flush();
            System.out.println("Please type either: yes / no");
            
            continue;
            
        }

    }
    
   	while (Again == 0) {                                 // Starts the main game loop
        
        int i = 0;
    	int war = 0;
    	int Score1 = 26;                                 //26 is half a deck, the amount a player will have
    	int Score2 = 26;                                 //the score subtracts until one person hits 0

        String[] ServerDeck = Arrays.copyOfRange(Cards(), 0, Cards().length/2);               //Cards() is a method which created a randomized 52 deck
        String[] ClientDeck = Arrays.copyOfRange(Cards(), Cards().length/2, Cards().length);  //These two lines split it in half and store in two arrays

        for (String u: ServerDeck){						 //prints the servers deck

            System.out.println(u);
        }
        
        
        
        for (String u: ClientDeck) {                     //sends the client's deck to the client so they can see it
        	
        	out.println(u);
        	out.flush();
        }

    
        str = in.readLine();                             //client wants the server to draw first
        System.out.println("client: "+str);
        
        
        while (Score1>1 && Score2>1) {                   //this statement is only redundancy; it mostly just a while (true)
        	
        	
        	
        	System.out.println("Enter d to Draw");
        	String Answer2 = scnr.nextLine();
        	
        	if (Answer2.equalsIgnoreCase("debugwar")) {  			//these tests the functionality of my war path
        		war = 1;
        	
        	}
        	if (Answer2.equalsIgnoreCase("debugwin")) {  			//used to escape and stop the code; i dont know how to do that otherwise XD
        		
        		break;
        		
        	}
        	
        	
        	
        	if (Answer2.equalsIgnoreCase("d")) {} else {
            	System.out.println("Please just enter d");
            	
            	continue;
            
        	}
        	
        	
        	int Q = 0;                                   //not sure, i had it for a purpose, but i cant remember why...
        	
        	
        	str = in.readLine();
        	Q = 1;
            System.out.println("client: "+str);
        	
            
            
        	if (Answer2.equalsIgnoreCase("d") && Q>0 || Answer2.equalsIgnoreCase("debugwar") || i != 26){             // i is being used to keep track of the rounds
        																											  // and determine which array number needs to be read
        		
        		int P1 = Integer.parseInt(ServerDeck[i].replaceAll("[\\D]",""));      //converts the arrays to int which
        		int P2 = Integer.parseInt(ClientDeck[i].replaceAll("[\\D]",""));      //can then be tested to see who wins
        		
        		
        		
        		
        		
        		if (P1 > P2) {                   //ok, a lot of messiness. most of it is just trying to keep
        										 //the in and out functions lined up with the client
        			Score1--;
        			
        			if (war>0) {
        				Score1= Score1 -3;
        				war=0;
        			}
        			
        			System.out.println("Server drawed " + P1 + " and Client drawed " + P2);
        			out.println("Server drawed " + P1 + " and Client drawed " + P2);
        			out.flush();
        			
        			System.out.println("Server Scores! :  "+ (Score1-26)*-1 + ":" + (Score2-26)*-1 + "   you have " + (i-25)*-1 + " cards left.");
        			out.println("Server Scores! :  "+ (Score1-26)*-1 + ":" + (Score2-26)*-1 + "   you have " + (i-25)*-1 + " cards left.");
        			out.flush();
        			
        			i++;
        			
        			if (i == 26) {                        //needed to be repeated three times to keep the client happy
                		
                		System.out.println("Game Over!");
                		out.println("Game");
            			out.flush();
                		
                		break;
                		
                	} else {
                		
                		out.println("Name");              //"name" is just a place holder name to tell the client to continue its loop
            			out.flush();
            			
                	}

        			continue;
        		
        			} else if (P2 > P1) {
        				
        				Score2--;
        				
        				if (war>0) {
            				Score2= Score2 -3;
            				war=0;
            			}
        				
        				System.out.println("Server drawed " + P1 + " and Client drawed " + P2);
            			out.println("Server drawed " + P1 + " and Client drawed " + P2);
            			out.flush();
        				
        				System.out.println("Client Scores! :  "+ (Score1-26)*-1 + ":" + (Score2-26)*-1 + "   you have " + (i-25)*-1 + " cards left.");
        				out.println("Client Scores! :  "+ (Score1-26)*-1 + ":" + (Score2-26)*-1 + "   you have " + (i-25)*-1 + " cards left.");
            			out.flush();
        				
            			i++;
            			
            			if (i == 26) {
                    		
                    		System.out.println("Game Over!");
                    		out.println("Game");
                			out.flush();
                    		
                    		break;
                    		
                    	} else {
                    	
                    		out.println("Name");                
                			out.flush();
                			
                    	}
           
        				continue;
        			
        		} else {
        			
        			i++;
        			i++;
        			i++;
        			
        			
        			
        			System.out.println("Its a Draw! Three cards have been placed; next round will determine who gets it!");
    				out.println("Its a Draw! Three cards have been placed");
        			out.flush();
    				out.println("next round will determine who gets it!");
        			out.flush();
        			
        			if (i == 26) {
                		
                		System.out.println("Game Over!");
                		out.println("Game");
            			out.flush();
                		
                		break;
                		
                	} else {
                		
                		out.println("Name");
            			out.flush();
            			
                	}
        			
        			
        			if (i>26) {
        				
        				break;
        			
        			}
        			
        			war = 1;
        			
        			continue;
        			
        		}
        	
        	}
   	
        }
        
        
        if (Score1 == Score2) {                              //deciding who won the game
        	System.out.println("You guy's Drawed!");
        	out.println("You guy's Drawed!");
			out.flush();
        	
        } 	else if (Score1 > Score2) {
        		System.out.println("Client WINS!");
        		out.println("Client WINS!");
    			out.flush();
        	
        } else {
    		System.out.println("Server WINS!");
    		out.println("Server WINS!");
			out.flush();
    	
    }
        int Continue = 1;
        while (Continue == 1) {
        Continue = 1;
        System.out.println("would you like play again?");     //asking if the server wants to play again
        String Answer3 = scnr.nextLine();
        
        
        
     
        
        if (Answer3.equalsIgnoreCase("yes")){
        	out.println("yes");
			out.flush();
			Again = 0; 
			Continue = 0;
        	
        } else if (Answer3.equalsIgnoreCase("no")) {
        	out.println("no");
			out.flush();
			Again = 1;
			Continue = 0;
			System.exit(1);
			
        	
        } else {
        	System.out.println("Please just use yes or no."); 
        }
     }
   }

        in.close();                              //closing everything
        out.close();
        conn.close();
        scnr.close();
        intout.close();
        server.close();
        
        
}


    public static String[] Cards() {             //learned from https://www.youtube.com/watch?v=GeEbs9-VLp0
                                                 //and modified-ish to work here

        String[] suit = {" Spade"," Diamond"," Club"," Heart"};
        String[] rank = {"2","3","4","5","6","7","8","9","10","Jack 11","Queen 12","King 13","Ace 14"};
        String[] deck = new String[52];

        for (int i = 0; i < deck.length; i++){
            deck[i] = rank[i%13] + suit[i/13];
        }

        
        for (int i = 0; i< deck.length; i++){
            int index = (int)(Math.random()*deck.length);

            String temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
        return deck;

    } 



}