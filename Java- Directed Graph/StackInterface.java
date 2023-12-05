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

/**An interface that describes the Stack's operations as references */
public interface StackInterface<T>
{
 /** Adds a new entry to the top of this stack.
 @param newEntry An object to be added to the stack. */
 public void push(T newEntry);

 /** Removes and returns this stack's top entry.
 @return The object at the top of the stack.
 @throws EmptyStackException if the stack is empty before
 the operation.*/
 public T pop();

 /** Retrieves this stack's top entry.
 @return The object at the top of the stack.
 @throws EmptyStackException if the stack is empty. */
 public T peek();

 /** Detects whether this stack is empty.
 @return True if the stack is empty. */
 public boolean isEmpty();

 /** Removes all entries from this stack.*/
 public void clear();
} // end StackInterface