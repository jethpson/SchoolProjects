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

public interface QueueInterface<T>
{
 /** Adds a new entry to the back of this queue.
@param newEntry An object to be added. */
public void enqueue(T newEntry);

 /** Removes and returns the entry at the front of this queue.
 @return The object at the front of the queue.
 @throws EmptyQueueException if the queue is empty before the operation.
 public T dequeue();

 /** Retrieves the entry at the front of this queue.
 @return The object at the front of the queue.
 @throws EmptyQueueException if the queue is empty. */
 public T getFront();

 /** Detects whether this queue is empty.
 @return True if the queue is empty, or false otherwise. */
 public boolean isEmpty();

 /** Removes all entries from this queue. */
 public void clear();
 } // end QueueInterface