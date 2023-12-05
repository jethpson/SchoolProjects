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


/** A interface that outlines the ADT tree.*/
public interface TreeInterface<T> 
{
	
	/** Gets the data in the root of this tree.
	 @param gets RootData for the object that is the tree's root.*/
	public T getRootData();

	/** Gets the height of this tree.
	 @param gets height for all the object that is in the tree.*/
	public int getHeight();

	/** Gets the number of Nodes of this tree.
	 @param gets number for all the nodes in the tree.*/
	public int getNumberOfNodes();

	/** Checks if tree is empty.
	 @param True or False depending on tree*/
	public boolean isEmpty();

	/** Clears Tree*/
	public void clear();
	
	
} // end TreeInterface
