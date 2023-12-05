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


import java.util.Stack;
/** A class ExpressionTree that extends BinaryTree and implements theExpressionTreeInterface. */
public class ExpressionTree extends BinaryTree<String> implements ExpressionTreeInterface 
{

	/** Takes a postfix and creates a binaryTree.
	@return The Input: IO through print */
	public ExpressionTree(String[] postfix) 						
	{
		Stack<BinaryNode<String>> stack = new Stack<>();
		int operandCount = 0;
		

		for (String token : postfix) 
		{
			
			if (!isOperator(token)) 
			{
				stack.push(new BinaryNode<>(token)); 				//Create a new BinaryNode with the token.
				operandCount++;
				 
			} else 
			{
				
				if (stack.size() < 2) {
	                throw new IllegalArgumentException("Improperly formed postfix expression: not enough operands for operator");
	            }
				
				BinaryNode<String> rhs = stack.pop();
				BinaryNode<String> lhs = stack.pop();
				BinaryNode<String> parent = new BinaryNode<>(token, lhs, rhs); //Create a new BinaryNode with the token as its data and lhs and rhs as its
				operandCount--;              								   //left and right children
				stack.push(parent);
				
			}
		}
		if (stack.size() != 1 || operandCount != 1) {
	        throw new IllegalArgumentException("Improperly formed postfix expression: too many operands or operators");
	    }
		
		setRootNode(stack.pop()); 			//The last item in the stack will be the root of the expression tree
		
		
		System.out.print("\n\nInput: ");	//Prints Input IO.
		
		for (String str : postfix)
			System.out.print(str + " ");
		
	}// end ExpressionTree

	
	/** Takes a String and checks if it is a +, -, *, /.
	@return True or False */
	private boolean isOperator(String token) 
	{
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
		
	}// end isOperator

	
	
	/** Display Tree method following leaf's, leftchild:root:rightChild.
	@return The output IO to evaluate */
	public void displayTree() 				
	{
		displayTree(getRootNode(), 0);
		
	}// end displayTree

	private void displayTree(BinaryNode<String> node, int offs) 
	{
		
		if (node == null)
		{
			return;
			
		}

		
		if (!isOperator(node.getData()))
			System.out.println(node.getData());
		

		
		displayTree(node.getLeftChild(), offs + 1);			//Print the left and right trees
		displayTree(node.getRightChild(), offs + 1);

		
		
		if (!node.isLeaf() && node.getLeftChild() != null && node.getRightChild() != null) 			//If a non-leaf node, print.
		{
			System.out.println(node.getLeftChild().getData() + " : " + node.getData() + " : " + node.getRightChild().getData());
			
		}
		
	}// end displayTree

	
	
	/** Creates the rest of the required IO, also returns the answer.
	@return The remaining IO through print */
	public int evaluate() 			
	{
		System.out.print("\nValue: " + evaluate(getRootNode()) + "\n\n");
		System.out.println("Postorder Traversal:");

		displayTree();

		return evaluate(getRootNode());
		
	} // end evaluate
	

	private int evaluate(BinaryNode<String> rootNode) 
	{
		int result;
	
		if (rootNode == null)
			result = 0;
		
		else if (rootNode.isLeaf()) 
		{
			String variable = rootNode.getData();
			result = getValueOf(variable);
		
		} else 
		{
			int firstOperand = evaluate(rootNode.getLeftChild());
			int secondOperand = evaluate(rootNode.getRightChild());
			String operator = rootNode.getData();
			result = compute(operator, firstOperand, secondOperand);
		
		}

		return result;
		
	} // end evaluate

	
	/** Turns String variable into an Interger.
	@return interger to evaluate */
	public int getValueOf(String variable)  		
	{
	
		try 
		{
			return Integer.parseInt(variable);
		
		} catch (NumberFormatException e) 
		{
			throw new IllegalArgumentException("Invalid variable: " + variable);
		
		}
		
	}// end getValueOf

	
	/** computes two Operands.
	@return interger to evaluate */
	public int compute(String operator, int firstOperand, int secondOperand) 		
	{
	
		switch (operator) 
		{
		case "+":
			return firstOperand + secondOperand;
		case "-":
			return firstOperand - secondOperand;
		case "*":
			return firstOperand * secondOperand;
		case "/":
			return firstOperand / secondOperand;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
			
		}
		
	}// end compute

} // end ExpressionTree
