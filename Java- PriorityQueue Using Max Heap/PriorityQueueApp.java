//
// Name: Thompson, Jacob
// HomeWork: 2
// Due: 4/26/2023
// Course: cs-2400-03-sp23
//
// Description:
// Implement an MaxHeapPriorityQueue Using A MaxHeap. Create a MaxHeapPriorityQueueApp java file which takes a set of numbers
// and Turns them into a MaxHeapPriorityQueue where they can them be traversed in the MaxHeap.
// As well as, implementing a debug method ShowHeap which adheres to printing leafs as they happen, then (left : root : right) using PostOrder.
//


public class PriorityQueueApp 
{
	
	public static void main(String[] args) 
	{
			
	System.out.println("Priority Queue by J. Thompson");
	System.out.print("\nValues enter: 68 15 10 8 35 14");
	
	PriorityQueueInterface<Integer> priorityQueue = new MaxHeapPriorityQueue<>(); //Creates a priorityQueue
	
	
	priorityQueue.add(68);														  //Adds hard-coded set of numbers
	priorityQueue.add(15);
	priorityQueue.add(10);
	priorityQueue.add(8);
	priorityQueue.add(35);
	priorityQueue.add(14);
	
	
	((MaxHeapPriorityQueue<Integer>) priorityQueue).showHeap();					  //call the showHeap method
	
	
	System.out.println("\n\nlist of values as they are being remove from the PQ");
	
	while(!priorityQueue.isEmpty())												  //Empty out the queue
	System.out.println(priorityQueue.remove());
	
	}
}
