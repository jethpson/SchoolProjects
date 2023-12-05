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
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements ListInterface<T> {

	private Node firstNode;
	private Node lastNode;

	private int numberOfEntries;

	public void IteratorForLinkedList() {
		initializeDataFields();
	} // end default constructor

	public SinglyLinkedList() {
		initializeDataFields();
	} // end default constructor

	
	
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);

		if (firstNode == null)
			firstNode = newNode;
		else // Add to end of nonempty list
		{
			lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if
		numberOfEntries++;
	} // end add

	
	
	public void add(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);
			if (givenPosition == 1) // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else // Case 2: List is not empty
			{ // and givenPosition > 1
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if
			numberOfEntries++;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	
	
	private Node getNodeAt(int givenPosition) {

		Node currentNode = firstNode;

		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		return currentNode;
	} // end getNodeAt

	
	
	
	
	public T remove(int givenPosition) {
		throw new UnsupportedOperationException("remove() is not supported " + "by this list");
	} // end remove

	public boolean isEmpty() {
		throw new UnsupportedOperationException("isEmpty() is not supported " + "by this list");
	} // end isEmpty

	public T[] toArray() {
		throw new UnsupportedOperationException("toArray() is not supported " + "by this list");
	} // end toArray

	public T replace(int givenPosition, T newEntry) {
		throw new UnsupportedOperationException("replace() is not supported " + "by this list");
	} // end replace

	public T getEntry(int givenPosition) {
		throw new UnsupportedOperationException("getEntry() is not supported " + "by this list");
	} // end getEntry

	public boolean contains(T anEntry) {
		throw new UnsupportedOperationException("contains() is not supported " + "by this list");
	} // end contains

	public final void clear() // Note the final method
	{
		throw new UnsupportedOperationException("clear() is not supported " + "by this list");
	} // end clear

	public int getLength() {
		throw new UnsupportedOperationException("getLength() is not supported " + "by this list");
	} // end getLength

	
	
	private void initializeDataFields() {
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	
	
	private class Node {
		
		private T data; // Entry in bag
		private Node next; // Link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructors

		private T getData() {
			return data;
		} // end getData

		@SuppressWarnings("unused")
		private void setData(T newData) {
			data = newData;
		} // end setData

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNex

	}

	
	
	
	public Iterator<T> iterator() {
		return new IteratorForLinkedList();
	} // end iterator

	public Iterator<T> getIterator() {
		return iterator();
	}// end getIterator

	
	
	
	private class IteratorForLinkedList implements Iterator<T> {

		private Node nextNode;

		private IteratorForLinkedList() {
			nextNode = firstNode;

		} // end default constructor

		public T next() {
			T result;
			if (hasNext()) {
				result = nextNode.getData();
				nextNode = nextNode.getNextNode(); // Advance iterator
			} else

				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");

			return result; // Return next entry in iteration
		} // end next

		public boolean hasNext() {
			return nextNode != null;
		} // end hasNext

		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported " + "by this iterator");
		} // end remove
	}


}

