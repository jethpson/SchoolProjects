//
// Name: Thompson, Jacob
// Project: 3
// Due: 3/24/2023
// Course: cs-2400-03-sp23
//
// Description:
// In this project we needed to create A hashed Dictionary and an application which utilizes it.
// The Hashed Dictionary needs to incorporate the methods add, getValue, and getKeyIterator.
// It also needs to include a method to get the amount of collisions that happened in a Dictionary.
//
// The aplication's goal is to read in a text document and utilize a Dictionary to store and count the collisions which happen.
// It also must print out the word count of each word. 
//


import java.util.Scanner;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class WordFrequency {

	public static void main(String[] args) {

		DictionaryInterface<Integer, String> hashtable = new HashedDictionary<>(1361);			//Creates a HashedDictionary named hashtable using length 1361.
		DictionaryInterface<Integer, String> hashtable2 = new HashedDictionary<>(1637);			//Creates a HashedDictionary named hashtable2 using length 1637.
		DictionaryInterface<Integer, String> hashtable3 = new HashedDictionary<>(2011);			//Creates a HashedDictionary named hashtable3 using length 2011.
		
		DictionaryInterface<Integer, String> counthashtable = new HashedDictionary<>(1361);		//Creates a HashedDictionary named counthashtable which will be used to 
																								//store the word count of each word using the same key as hashtable.
																								
		
		
		int unique = 0;								//Counts the amount of unique words.
		int size = 0;								//Keeps track of the document size.
		
		int Key;									//both the Key and Value initialization 
		String Value;								
		
		String[] Frequency = new String[10000];		//Temporary array so that I can check the document after I read it into the dictionary.
		
		int FC = 0;									//The amount of times a word appears inside a document.
		int temp = 0;								// temp int is used to store an iterator for hashtable named move. Traverses words.
		int temp2 = 0;								// temp2 int is used to store an iterator for counthashtable named move2. Traverses word count.


		

		
		System.out.println("Word Frequency by J. Thompson\n\nCount Word\n----- --------------------");

		
		
	try 
		{
			File newFile;
			newFile = new File("usconstitution.txt");
			Scanner scnr = new Scanner(newFile);

			
			
			while (scnr.hasNext()) 									//Reads file into the Frequency array.
			{
				size++;
				Frequency[size-1] = scnr.next().toLowerCase();
			}

			scnr.close();

		} catch (FileNotFoundException e) 
		{
			System.out.println("File not found: " + e.getMessage());
		}

		
		
		
		
	for (int i = 0; i < size; i++) 
		{

			Value = Frequency[i];
			Key = 0;
			FC = 0;

			
			for (int t = 0; t < size; t++) 							//Searches document to see how many times a word appears.
			if (Value.equals(Frequency[t]))
			FC++;
			

				
			int n = Value.length();								 	//Convert a word into a key.
			for (int j = 0; j < n; j++)
				Key = 31 * Key + Value.charAt(j);

			
			counthashtable.add(Key, String.valueOf(FC));			//Adds the same key to all hashtables, count hashtable is given a keys specific 
			hashtable.add(Key, Value);								//word count and the rest receive the word.
			hashtable2.add(Key, Value);
			hashtable3.add(Key, Value);

		}

		
		
	Iterator<Integer> move = hashtable.getKeyIterator();			//Creates keyIterators.
	Iterator<Integer> move2 = counthashtable.getKeyIterator();

		
while (move.hasNext()) 
	{
				
		unique++;					//Adds to unique count.
		
		temp = move.next();			//Sets temp.
				
		if (move2.hasNext()) 		//Sets temp2.
		temp2 = move2.next();
				
			
		System.out.println(counthashtable.getValue(temp2) + "      " + hashtable.getValue(temp));
				
	}


		
System.out.println("\nUnique words = " + unique);
System.out.println("\nTable\nLength  Collision\n1361    "+ ((HashedDictionary<Integer, String>) hashtable).getCollisionCount());
System.out.println("1637    " + ((HashedDictionary<Integer, String>) hashtable2).getCollisionCount());
System.out.println("2011    " + ((HashedDictionary<Integer, String>) hashtable3).getCollisionCount());

		
	}
}
