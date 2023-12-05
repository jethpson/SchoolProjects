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
