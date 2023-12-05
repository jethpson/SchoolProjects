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


/** A interface that outlines the ExpressionTree that extends to BinaryTreeInterface.*/
public interface ExpressionTreeInterface extends BinaryTreeInterface<String>
{
	 
		 
	 /** Computes the value of the expression in this tree.
	 @return The value of the expression. */
	 public int evaluate();
	 
 
} // end ExpressionTreeInterface
