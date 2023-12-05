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
/** A class that implements the ADT binary tree. */
public class BinaryTree<T> implements BinaryTreeInterface<T> 
{

	
	private BinaryNode<T> root; 			//Initialize root

	
	public BinaryTree() 					//Initialize BinaryTree, BinaryTree(), BinaryTree( , , ), and setTree.
	{
		root = null;
		
	} // end default constructor

	
	public BinaryTree(T rootData) 
	{
		root = new BinaryNode<>(rootData);
		
	} // end constructor

	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) 
	{
		initializeTree(rootData, leftTree, rightTree);
		
	} // end constructor

	
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) 
	{
		initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
		
	} // end setTree
	
	
	private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) 
	{
		root = new BinaryNode<>(rootData);
		if ((leftTree != null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root);
		if ((rightTree != null) && !rightTree.isEmpty()) 
		{
			
			if (rightTree != leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
			
		} // end if
		if ((leftTree != null) && (leftTree != this))
			leftTree.clear();
		
		if ((rightTree != null) && (rightTree != this))
			rightTree.clear();
		
	} // end initializeTree

	
	
	
	public void setRootData(T rootData) 		//Set, get, empty, and clear root data's and nodes.
	{
		root.setData(rootData);
		
	} // end setRootData

	
	public T getRootData() 
	{
		if (isEmpty()) 
		{
			throw new UnsupportedOperationException("Tree is empty");
		} else
			return root.getData();
		
	} // end getRootData

	
	public boolean isEmpty() 
	{
		return root == null;
		
	} // end isEmpty

	
	public void clear() 
	{
		root = null;
		
	} // end clear

	
	protected void setRootNode(BinaryNode<T> rootNode) 
	{
		root = rootNode;
		
	} // end setRootNode

	
	protected BinaryNode<T> getRootNode() 
	{
		return root;
		
	} // end getRootNode

	
	
		
		@Override											//Unsupported
		public Iterator<T> getPostorderIterator() 
		{
			throw new UnsupportedOperationException("getPostorderIterator() is not supported " + "by this Tree");
		}
	
		@Override
		public Iterator<T> getPreorderIterator() 
		{
			throw new UnsupportedOperationException("getPreorderIterator() is not supported " + "by this Tree");
		}
	
		@Override
		public Iterator<T> getInorderIterator() 
		{
			throw new UnsupportedOperationException("getInorderIterator() is not supported " + "by this Tree");
		}
	
		@Override
		public Iterator<T> getLevelOrderIterator() 
		{
			throw new UnsupportedOperationException("getLevelOrderIterator() is not supported " + "by this Tree");
		}

		
		
		
	@Override						//References.
	public int getHeight() 
	{
		return getHeight();
		
	}

	
	@Override
	public int getNumberOfNodes() 
	{
		return getNumberOfNodes();
		
	}

	
	
	
	@SuppressWarnings("hiding")		//The BinaryNode Class and its operations.
	class BinaryNode<T> 
	{
		private T data;
		private BinaryNode<T> leftChild;
		private BinaryNode<T> rightChild;

		public BinaryNode() 
		{
			this(null); // Call next constructor
			
		} // end default constructor

		public BinaryNode(T dataPortion) 
		{
			this(dataPortion, null, null); // Call next constructor
			
		} // end constructor

		public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild) 
		{
			data = dataPortion;
			leftChild = newLeftChild;
			rightChild = newRightChild;
			
		} // end constructor

		
		/** Retrieves the data portion of this node.
		 @return The object in the data portion of the node.*/
		public T getData() 
		{
			return data;
			
		} // end getData

		
		/** Sets the data portion of this node.
		@param newData The data object.*/
		public void setData(T newData) 
		{
			data = newData;
			
		} // end setData
		
		
		/** Sets this node's left child to a given node.
		@param newLeftChild A node that will be the left child. */
		public void setLeftChild(BinaryNode<T> newLeftChild) 
		{
			leftChild = newLeftChild;
			
		} // end setLeftChild

		
		/** Detects whether this node has a left child.
		@return True if the node has a left child.*/
		public boolean hasLeftChild() 
		{
			return leftChild != null;
			
		} // end hasLeftChild

	
		/** Retrieves the left child of this node.
		@return A reference to this node's left child. */
		public BinaryNode<T> getLeftChild() 
		{
			return leftChild;
			
		} // end getLeftChild

		
		
		
		/** Retrieves the Right child of this node.
		@return A reference to this node's tight child. */
		public BinaryNode<T> getRightChild() 
		{
			return rightChild;
			
		} // end getLeftChild

		
		
		/** Sets this node's Right child to a given node.
		@param newRightChild A node that will be the right child. */
		public void setRightChild(BinaryNode<T> newRightChild) 
		{
			leftChild = newRightChild;
			
		} // end setLeftChild


		/** Detects whether this node has a right child.
		@return True if the node has a right child.*/
		public boolean hasRightChild() 
		{
			return rightChild != null;
			
		} // end hasLeftChild

		
		/** Detects whether this node is a leaf.
		@return True if the node is a leaf. */
		public boolean isLeaf() 
		{
			return (leftChild == null) && (rightChild == null);
			
		} // end isLeaf

		
		/** Copies the subtree rooted at this node.
		@return The root of a copy of the subtree rooted at this node. */
		public BinaryNode<T> copy() 
		{
			BinaryNode<T> newRoot = new BinaryNode<>(data);
			
			if (leftChild != null)
				newRoot.setLeftChild(leftChild.copy());
			
			if (rightChild != null)
				newRoot.setRightChild(rightChild.copy());
			
			return newRoot;
			
		} // end copy
		
		
		/** Computes the height of the subtree rooted at this node.
		@return The height of the subtree rooted at this node. */
		public int getHeight() 
		{
			return getHeight(this); // Call private getHeight
			
		} // end getHeight
		
		private int getHeight(BinaryNode<T> node) 
		{
			int height = 0;
			if (node != null)
				height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
			
			return height;
			
		} // end getHeight

		
		/** Counts the nodes in the subtree rooted at this node.
		@return The number of nodes in the subtree rooted at this node. */
		public int getNumberOfNodes() 
		{
			int leftNum = 0;
			int rightNum = 0;
			
				if (hasLeftChild()) 
				{
					leftNum = leftChild.getNumberOfNodes();
				}
				
				if (hasRightChild()) 
				{
					rightNum = rightChild.getNumberOfNodes();
				}
				
			return 1 + leftNum + rightNum;
			
		}// end getNumberOfNodes

	} // end BinaryNode

} // end BinaryTree