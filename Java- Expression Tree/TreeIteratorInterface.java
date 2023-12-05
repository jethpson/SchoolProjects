//
// Name: Thompson, Jacob
// Project: 4
// Due: 4/13/2023
// Course: cs-2400-03-sp23
//
// Description:
// Implement an ExpressionTree Using A BinaryTree. Create a ExpressionTreeTest java file which takes postfix arguments from the command-line
// and Turns them into a Binary Tree where they can them be evaluated. With only + - * / operators.
// As well as, implementing a debug method DisplayTree which adheres to printing leafs as they happen, then (left node : root : right node).
//


import java.util.Iterator;
/** A interface that outlines the TreeIterator*/
public interface TreeIteratorInterface<T> 
{
	
	/** Creates an iterator that iterates in PreOrder order.*/
	public Iterator<T> getPreorderIterator();

	/** Creates an iterator that iterates in PostOrder order.*/
	public Iterator<T> getPostorderIterator();

	/** Creates an iterator that iterates in InOrder order.*/
	public Iterator<T> getInorderIterator();

	/** Creates an iterator that iterates in levelOrder order.*/
	public Iterator<T> getLevelOrderIterator();
	
	
}// end TreeIteratorInterface