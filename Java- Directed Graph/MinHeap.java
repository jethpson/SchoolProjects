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

import java.util.Arrays;

/** A class that implements the ADT minheap by using an array. */
public final class MinHeap<T extends Comparable<? super T>> implements MinHeapInterface<T> 
{
	
	private T[] heap;
	private int lastIndex;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	
	public MinHeap(int initialCapacity) 
	{

		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else 
			checkCapacity(initialCapacity);

		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		integrityOK = true;
	} 

	
	public MinHeap() 
	{
		
		this(DEFAULT_CAPACITY); 
	} // end MinHeap

	
	public T getMin() 
	{
		
		checkIntegrity();
		T root = null;
		
		if (!isEmpty())
			root = heap[1];
		
		return root;
	} // end getmin

	
	public boolean isEmpty() 
	{
		
		return lastIndex < 1;
	} // end isEmpty

	
	public int getSize() 
	{
		
		return lastIndex;
	} // end getSize

	
	public void add(T newEntry) 
	{
		
		checkIntegrity(); 
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		
		while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) < 0) 
		{
			
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		} // end while
		
		
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
		
		
		
	} // end add

	
	public T removeMin() 
	{
		
		checkIntegrity(); 
		T root = null;
		
		if (!isEmpty()) 
		{
			
			root = heap[1]; 
			heap[1] = heap[lastIndex]; 
			lastIndex--; 
			reheap(1); 
		} // end if
		
		
		return root;
	} // end removeMin

	
	public void clear() 
	{
		
		checkIntegrity();
		
		
		while (lastIndex > -1) 
		{
			
			heap[lastIndex] = null;
			lastIndex--;
		} // end while
		
		
		lastIndex = 0;
	} // end clear

	
	private void reheap(int rootIndex) 
	{
	    boolean done = false;
	    T orphan = heap[rootIndex];
	    int leftChildIndex = 2 * rootIndex;
	    
	    while (!done && (leftChildIndex <= lastIndex)) 
	    {
	        int smallerChildIndex = leftChildIndex;
	        int rightChildIndex = leftChildIndex + 1;
	        
	        if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[smallerChildIndex]) < 0) 
	        {
	            smallerChildIndex = rightChildIndex;
	        }
	        
	        if (orphan.compareTo(heap[smallerChildIndex]) > 0) 
	        {
	            heap[rootIndex] = heap[smallerChildIndex];
	            rootIndex = smallerChildIndex;
	            leftChildIndex = 2 * rootIndex;
	        } else {
	            done = true;
	        }
	    }
	    
	    heap[rootIndex] = orphan;
	}

	
	public void checkIntegrity() 
	{
		
		if (!integrityOK) 
		{
			
			throw new SecurityException("Heap object is corrupt.");
		}
	}

	
	private void checkCapacity(int capacity) 
	{
		
		if (capacity > MAX_CAPACITY) 
		{
			
			throw new IllegalStateException("Attempt to create a heap whose capacity exceeds allowed maximum.");
		}
	}

	
	private void ensureCapacity() 
	{
		
		if (lastIndex == heap.length - 1) 
		{
			
			int newCapacity = heap.length * 2;
			checkCapacity(newCapacity - 1); 
			heap = Arrays.copyOf(heap, newCapacity);
		}
	}

	
	public void postorderTraversal() 
	{
	    
	    postorderTraversal(1);   
	}
	

	private void postorderTraversal(int index) 
	{
		
	    if (index <= lastIndex) 
	    {
	    	
	        postorderTraversal(2 * index + 1); 		//right subtree
	        postorderTraversal(2 * index); 			//left subtree
	        
	        if (2 * index > lastIndex)
	        {
	        	
	        System.out.println(heap[index]); 		//print leaf node
	        } else 
	        {
	        	
	        System.out.print(heap[2*index] + " : "); 
	        System.out.print(heap[ index] + " : "); //print left child
	        if (2 * index + 1 <= lastIndex) 
	        {
	            	
	        System.out.print(heap[2 * index + 1]); //print right child
	        } else
	        {
	            	
	        System.out.print("."); 				   //fill . 
	        }
	            
	        System.out.println();				   //next line
	        }
	    }
	}
	
	
}