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


/** An interface for the ADT maxheap.*/
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
	/**
	 * Adds a new entry to this heap.
	 * 
	 * @param newEntry An object to be added.
	 */
	public void add(T newEntry);

	/**
	 * Removes and returns the largest item in this heap.
	 * 
	 * @return Either the largest object in the heap or,
	 *         if the heap is empty before the operation, null.
	 */
	public T removeMax();

	/**
	 * Retrieves the largest item in this heap.
	 * 
	 * @return Either the largest object in the heap or,
	 *         if the heap is empty, null.
	 */
	public T getMax();

	/**
	 * Detects whether this heap is empty.
	 * 
	 * @return True if the heap is empty, or false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Gets the size of this heap.
	 * 
	 * @return The number of entries currently in the heap.
	 */
	public int getSize();

	/** Removes all entries from this heap. */
	public void clear();
} // end MaxHeapInterface
