//
// Name: Thompson, Jacob
// HomeWork: 2
// Due: 4/26/2023
// Course: cs-2400-03-sp23
//
// Description:
// Implement an MaxHeapPriorityQueue Using A MaxHeap. Create a MaxHeapPriorityQueueApp java file which takes a set of numbers
// and Turns them into a MaxHeapPriorityQueue where they can them be traversed in the MaxHeap.
// As well as, implementing a debug method ShowHeap which adheres to printing leafs as they happen, then (left : root : right) using PostOrder.
//
	

import java.util.Arrays;

/** A class that implements the ADT maxheap by using an array. */
public final class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> 
{
	
	
	private T[] heap;
	private int lastIndex;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	
	public MaxHeap(int initialCapacity) 
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

	
	public MaxHeap() 
	{
		
		this(DEFAULT_CAPACITY); 
	} // end MaxHeap

	
	public T getMax() 
	{
		
		checkIntegrity();
		T root = null;
		
		if (!isEmpty())
			root = heap[1];
		
		return root;
	} // end getMax

	
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
		
		
		while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0) 
		{
			
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		} // end while
		
		
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	} // end add

	
	public T removeMax() 
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
	} // end removeMax

	
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
			
			int largerChildIndex = leftChildIndex; 
			int rightChildIndex = leftChildIndex + 1;
			
			
			if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) 
			{
				
				largerChildIndex = rightChildIndex;
			} // end if
			
			
			if (orphan.compareTo(heap[largerChildIndex]) < 0) 
			{
				
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
			} else
				
				done = true;
		} // end while
		
		
		heap[rootIndex] = orphan;
	} // end reheap

	
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