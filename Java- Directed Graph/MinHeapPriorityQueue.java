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

/**A class that implements the ADT priority queue*/
public final class MinHeapPriorityQueue<T extends Comparable <? super T>> implements PriorityQueueInterface<T> 
{

	
	private MinHeapInterface<T> pq;
	
	
	public MinHeapPriorityQueue() 
	{
		
		pq = new MinHeap<>();
	}
	
	
	public void add(T newEntry) 
	{
		
		pq.add(newEntry);
	}
	
	
	public T remove() 
	{
		
		return pq.removeMin();
	}
	
	
	public T peek() 
	{
		
		return pq.getMin();
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
		((MinHeap<T>) pq).postorderTraversal();		
	}

}
