// 
//  Name:   Thompson, Jacob 
//  Project: #2 
//  Due:      2/21/2023 
//  Course:  cs-2400-03-sp23
// 
//  Description: 
//    Implement the interface StackInterface and the class LinkedStack.
//    Allow the class Expressions the ability to use that interface.
//    Read the ExpressionTest args from the command-line and feed the results to Expression.java,
//    Expression.java has two methods, convertToPostfix and evaluatePostfix.
//    ConvertToPostfix will take in Infix arguments and convert them to Postfix using the linked stack, handling all exceptions as necessary.
//    EvaluatePostfix will take in a postfix argument and evaluate through the linked stack, handling all exceptions as necessary.
//    Then Show the testing of all the interfaces that are not used above.
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