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


/**A class that implements the ADT priority queue*/
public final class MaxHeapPriorityQueue<T extends Comparable <? super T>> implements PriorityQueueInterface<T> 
{

	
	private MaxHeapInterface<T> pq;
	
	
	public MaxHeapPriorityQueue() 
	{
		
		pq = new MaxHeap<>();
	}
	
	
	public void add(T newEntry) 
	{
		
		pq.add(newEntry);
	}
	
	
	public T remove() 
	{
		
		return pq.removeMax();
	}
	
	
	public T peek() 
	{
		
		return pq.getMax();
	}
	
	
	public boolean isEmpty() 
	{
		
		return pq.isEmpty();
	}
	
	
	public int getSize() 
	{
		
		return pq.getSize();
	}

	
	@Override
	public void clear() 
	{
		
		pq.clear();
	}
	
	
	public void showHeap() 
	{
		
		System.out.println("\n\nHeap: ");
		((MaxHeap<T>) pq).postorderTraversal();		
	}

}
