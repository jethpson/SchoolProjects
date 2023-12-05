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

/** An interface for the ADT maxheap.*/
public interface MinHeapInterface<T extends Comparable<? super T>>
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
	public T removeMin();

	/**
	 * Retrieves the largest item in this heap.
	 * 
	 * @return Either the largest object in the heap or,
	 *         if the heap is empty, null.
	 */
	public T getMin();

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
