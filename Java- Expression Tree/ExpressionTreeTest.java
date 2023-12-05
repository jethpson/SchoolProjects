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


/** A class ExpressionTreeTest that utilizes the ExpressionTree to evaluate and print a postfix Expression.*/
public class ExpressionTreeTest 
{
	
	public static void main(String[] args) 
	{
			
	System.out.println("Expression Tree by J. Thompson");

		for (String arg: args) 
		{
			ExpressionTreeInterface et = new ExpressionTree(arg.split(" "));
			et.evaluate();
		}
			
	}
	
}









