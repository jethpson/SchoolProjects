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



import java.util.Arrays;
import java.util.EmptyStackException;

 /**
 A class of stacks whose entries are stored in an array.
 */
 public final class LinkedStack<T> implements StackInterface<T>
 {
 private T[] stack; // Array of stack entries
 private int topIndex; // Index of top entry
 private boolean integrityOK;
 private static final int DEFAULT_CAPACITY = 50;
 //private static final int MAX_CAPACITY = 10000;

 
 
 
 public LinkedStack()
 {
 this(DEFAULT_CAPACITY);
 } // end default constructor

 
 
 /** Creates a LinkedStack.*/
 public LinkedStack(int initialCapacity)
 {
 integrityOK = false;
 checkCapacity(initialCapacity);

 // The cast is safe because the new array contains null entries
 @SuppressWarnings("unchecked")
 T[] tempStack = (T[])new Object[initialCapacity];
 stack = tempStack;
 topIndex = -1;
 integrityOK = true;
 } // end constructor

 
 

 private void checkCapacity(int capacity)
 {
 if(capacity > DEFAULT_CAPACITY)
 throw new IllegalStateException("Attempt to create a bag whose "+
 "capacity exeeds allowed "+
 "maximum of "+ DEFAULT_CAPACITY);
 } // end checkCapacity
 
 
 
 
/** Adds a new entry to the top of this stack.
@param newEntry An object to be added to the stack. */
public void push(T newEntry) {
	checkIntegrity();
	ensureCapacity();
	stack[topIndex + 1] = newEntry;
	topIndex++;
} // end push
 



private void ensureCapacity() {
	if (topIndex == stack.length - 1) // If array is full, double its size
	{
		int newLength = 2 * stack.length;
		checkCapacity(newLength);
		stack = Arrays.copyOf(stack, newLength);
	} // end if
} // end ensureCapacity



/** Retrieves this stack's top entry.
@return The object at the top of the stack.
@throws EmptyStackException if the stack is empty. */
public T peek() {
	checkIntegrity();
	if (isEmpty())
		throw new EmptyStackException();
	else
		return stack[topIndex];
} // end peek




/** Removes and returns this stack's top entry.
@return The object at the top of the stack.
@throws EmptyStackException if the stack is empty before
the operation.*/
public T pop() {
	checkIntegrity();
	if (isEmpty())
		throw new EmptyStackException();
	else {
		T top = stack[topIndex];
		stack[topIndex] = null;
		topIndex--;
		return top;
	} // end if
} // end pop




/** Detects whether this stack is empty.
@return True if the stack is empty. */
public boolean isEmpty() {
	return topIndex < 0;
} // end isEmpty



/** Removes all entries from this stack.*/
public void clear() {
	
	while (!isEmpty()) 
	{
		pop();
	}
} // end clear




private void checkIntegrity() {
	if (!integrityOK)
		throw new SecurityException("ArrayBag object is corrupt.");
} // end checkIntegrity

} // end ArrayStack