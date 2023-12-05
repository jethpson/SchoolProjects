//
// Name: Thompson, Jacob
// Project: 5
// Due: 5/12/23
// Course: cs-2400-03-sp23
//
// Description:
// Implement the graph ADT to take in a set of airport's IATA and there full information.
// Also, take in another set of Airport IATA and their destination, and finally its distance.
// With this information build the graph using dictionaries, queues, stacks, and lists.
// Create a menu that can ask for full information, find best routes, insert routes, and remove routes.
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class AirportApp
{
		
	public static void main(String[] args) 
	{
			
	System.out.println("Airports v0.1 by J. Thompson\n");
	
	//initialize Scanner and Menu Variables
	Scanner scnr = new Scanner(System.in);
	String menuinput = "";
	
	//create a DirectedGraph
    GraphInterface<String> route = new DirectedGraph<>();
	
	//create a Dictionary
	DictionaryInterface<String, String> hashtable = new HashedDictionary<>(251);
	
	//load values into ADT's
	LoadDirandDic(route,hashtable);

    //Menu With All The Options : Includes showGraph() debug option through "debug".	
	while (!menuinput.equals("E")) 
		{
		
		System.out.print("Command? ");
		
		menuinput = scnr.next();
		
        
        switch (menuinput) 
        	{
        
        	case "Q":
	
	            System.out.print("Airport code: ");								//Asks for an IATA code and checks if it
	            String IATA = scnr.next();					   				    //exists with Validintput method; Then returns the
	            																//value of the dictionary at that code.
	            
	            if(Validinput(IATA,hashtable))
	            	System.out.print(hashtable.getValue(IATA));
	            
	            break;
	            
        	case "D":
	
	        	try 
	        	{
	        			
	        	StackInterface<String> test = new LinkedStack<>(); 				//Gets two IATA codes from the console
	        																	//Separated by a space, then checks 
	        																	//if they exist. If so, then they are put
	        	@SuppressWarnings("resource")									//into .getCheapestPath with an already
				Scanner Input = new Scanner(System.in);							//created Stack.
	        	System.out.print("Airport codes: ");
	        	
	        	
	        	String DIATA = Input.nextLine();
	        	String[] DIATAandIATA = DIATA.split(" ");
	        	String DIATA1 = DIATAandIATA[0];
	            String DIATA2 = DIATAandIATA[1];
	            
	            
	            if(Validinput(DIATA1,hashtable)&&Validinput(DIATA2,hashtable)) 
	            {
	            	
	            	int Distance = (int)route.getCheapestPath(DIATA1,DIATA2,test);
	            	if(Distance==0)
	            		System.out.print("Airports not connected");
	            	else
	            	{
	            		System.out.print("The minimum distance between "+hashtable.getValue(DIATA1)+" to "+hashtable.getValue(DIATA2)+" is "+Distance+" through the route:\n");
	            
	            		while(!test.isEmpty())
	            			System.out.println(hashtable.getValue(test.pop())); 
	            	}
	            	
	            }   
	        	} catch(Exception e) 
	        	{
	        		
	        		System.out.print("Airport code unknown");
	        	}
	        	
	            break;
	            
        	case "I":	
	
	            try 
	            {
	            	
	            @SuppressWarnings("resource")									
				Scanner Input = new Scanner(System.in);							//Takes in two IATA codes and
	            System.out.print("Airport codes and distance: ");				//an integer from the console
	            															    //checks if they exists and uses
	            																//.addEdge to add them to the graph.
	            String IIATA = Input.nextLine();								//Checks for existing route.
	        	String[] IIATAandIATA = IIATA.split(" ");
	        	String IIATA1 = IIATAandIATA[0];
	            String IIATA2 = IIATAandIATA[1];
	            String IDistance = IIATAandIATA[2];
	            
	            
	            if(Validinput(IIATA1,hashtable)&&Validinput(IIATA2,hashtable)) 
	            {
	            	
	            	 if(route.addEdge(IIATA1, IIATA2,Integer.parseInt(IDistance)))
	            	 {
	            		 //add route
	            	 }
	            	 else
	            		 System.out.print("Airport Route Already Exists");
	            } 
	            		            	           
	            } catch(Exception e) 
	            {
	        		System.out.print("Airport code unknown");
	        	}
	            
	            break;
	            
        	case "R":
	
	        	 try 
		            {
	        		 
		            @SuppressWarnings("resource")
					Scanner Input = new Scanner(System.in);							//Reads in two IATA codes from 
		            System.out.print("Airport codes: ");							//console, checks validity
		            																//use the .addEdge to set the route
		            																//weight to 0.
		            String RIATA = Input.nextLine();
		        	String[] RIATAandIATA = RIATA.split(" ");						//The vertex class connect method will handle 
		        	String RIATA1 = RIATAandIATA[0];								//the new weight and remove the route
		            String RIATA2 = RIATAandIATA[1];								//from the linked list.
		            
		            
		            if(Validinput(RIATA1,hashtable)&&Validinput(RIATA2,hashtable)) 
		            {
		            	
		            	 if(route.addEdge(RIATA1, RIATA2,0.0)) 
		            		 System.out.print("The connection from "+hashtable.getValue(RIATA1)+" to "+hashtable.getValue(RIATA2)+" has been removed.");
		            	 
		            } else
		            	System.out.print(" : Airport connection unknown");
		            		            	           
		            } catch(Exception e) 
		            {
		        		System.out.print("Airport connection unknown");
		        	}
	        	 
	            break;
	            
        	case "H":
	
	            System.out.println("Q Query the airport information by entering the airport code.");
	            System.out.println("D Find the minimum distance between two airports.");
	            System.out.println("I Insert a connection by entering two airport codes and distance.");
	            System.out.println("R Remove an existing connection by entering two airport codes.");
	            System.out.println("H Display this message.");
	            System.out.println("E Exit.");
	            
	            break;
	            
        	case "E":
	
	            break;
	            
			 case "debug":
				 
				((DirectedGraph<String>) route).showGraph();
				
			 	break;
			 	
	        default:
	        	
	            System.out.print("Invalid command");
	            
        	}
    
    System.out.println(); 
    
		}
	
	scnr.close();
	
	}
	
	
	
	
	public static Boolean LoadDirandDic(GraphInterface<String> route,DictionaryInterface<String, String> hashtable)
	{
				
		File airports = new File("airports.csv");

		
	    try (Scanner scanner = new Scanner(airports)) 
	    {
	    	
	        while (scanner.hasNextLine())
	        {
	        	
	            String line = scanner.nextLine();
	            String[] fields = line.split(",", 2);
	            String airportCode = fields[0];
	            String airportName = fields[1];           
	            route.addVertex(airportCode);
	            hashtable.add(airportCode, airportName);
	            
	        } 
	        
	        scanner.close();
	        
	    } catch (FileNotFoundException e) 
	    {
	    	
	        System.out.println("File not found: " + e.getMessage());
	    }
	    
	    
	    
	    
	    File distances = new File("distances.csv");

	    try (Scanner scanner = new Scanner(distances)) 
	    {
	    	
	        while (scanner.hasNextLine())
	        {
	        	
	        	String line = scanner.nextLine();
	            String[] fields = line.split(",");
	            String airportCodeTo = fields[0];
	            String airportNameFrom = fields[1];
	            String Distance = fields[2];
	            route.addEdge(airportCodeTo, airportNameFrom, Integer.parseInt(Distance));
	            
	        }
	        scanner.close();
	        
	    } catch (FileNotFoundException e) 
	    {
	    	
	        System.out.println("File not found: " + e.getMessage());
	    }

		return null;
	}
	

	
	
	public static Boolean Validinput(String IATA,DictionaryInterface<String, String> hashtable) 
	{
		
		Iterator<String> move = hashtable.getKeyIterator();
		
		
		while (move.hasNext()) 
		{
			
			String temp = move.next();
			
			if(temp.equals(IATA)) 
			{
				
				return true;
			}
			
		}
			
		System.out.print("Airport code unknown");	
		return false;
	}
	
}
