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


/** A interface that outlines the Binary tree That extends to TreeInterface and TreeIteratorInterface.*/
public interface BinaryTreeInterface<T> extends TreeInterface<T>, TreeIteratorInterface<T>
{

		 
	 /** Sets the data in the root of this binary tree.
	 @param rootData The object that is the data for the tree's root.
	 */
	 public void setRootData(T rootData);
	
	 
	 /** Sets this binary tree to a new binary tree.
	 @param rootData The object that is the data for the new tree's root.
	 @param leftTree The left subtree of the new tree.
	 @param rightTree The right subtree of the new tree. */
	 public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			 						 BinaryTreeInterface<T> rightTree);
 
} // end BinaryTreeInterface