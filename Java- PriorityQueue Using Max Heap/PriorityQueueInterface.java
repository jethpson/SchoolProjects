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


/**An interface for the ADT priority queue.*/
public interface PriorityQueueInterface<T extends Comparable<? super T>>
{
	/**
	 * Adds a new entry to this priority queue.
	 * 
	 * @param newEntry An object to be added.
	 */
	public void add(T newEntry);

	/**
	 * Removes and returns the entry having the highest priority.
	 * 
	 * @return Either the object having the highest priority or, if the
	 *         priority queue is empty before the operation, null.
	 */
	public T remove();

	/**
	 * Retrieves the entry having the highest priority.
	 * 
	 * @return Either the object having the highest priority or, if the
	 *         priority queue is empty, null.
	 */
	public T peek();

	/**
	 * Detects whether this priority queue is empty.
	 * 
	 * @return True if the priority queue is empty, or false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Gets the size of this priority queue.
	 * 
	 * @return The number of entries currently in the priority queue.
	 */
	public int getSize();

	/** Removes all entries from this priority queue. */
	public void clear();
} // end PriorityQueueInterface
