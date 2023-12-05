// 
//  Name:   Thompson, Jacob 
//  Homework:  #1 
//  Due:        3/14/23 
//  Course:  cs-2400-03-sp23 
// 
//  Description: 
//    For this homework, we needed to implement a singlylinklist that had an inner interator method, 
//    which a java file could use to iterate through a created list.
// 

import java.util.Iterator;

public class ListIteratorDemo {

	public static void main(String[] args) {
		
		ListInterface<Integer> myList = new SinglyLinkedList<>();	//Create a singlelinklist
		
		myList.add(15);												//Add five integers to the list
		myList.add(25);
		myList.add(35);
		myList.add(45);
		myList.add(2, 55);
		
		Iterator<Integer> move = myList.getIterator();				//Create Iterator
		
		System.out.println("List J. Thompson\n");
		
		while (move.hasNext())										//Use .hasNext() and .next() to print the contents of the singlelinklist.
			System.out.println(move.next());
		
		
	} // end main
	
} // end ListIteratorDemo
