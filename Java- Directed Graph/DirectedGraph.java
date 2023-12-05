//
// Name: Thompson, Jacob
// Project: 5
// Due: 5/12/23
// Course: cs-2400-03-sp23
//
// Description:
// Implement the graph ADT to take in a set of airport's IATA and there full information.
// Also, take in another set of Airport IATA and their destination, and finally its distance.
// With this information build the graph using dictionaries, queues, stacks, and lists.
// Create a menu that can ask for full information, find best routes, insert routes, and remove routes.
//

import java.util.Iterator;

public class DirectedGraph<T> implements GraphInterface<T> 
{
	
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;

	
	public DirectedGraph() 
	{
		
		vertices = new HashedDictionary<>(251);
		edgeCount = 0;
	} // end default constructor

	
	public boolean addVertex(T vertexLabel) 
	{
		
		VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null; // Was addition to dictionary successful?
	} // end addVertex

	
	public boolean addEdge(T begin, T end, double edgeWeight) 
	{
		
		boolean result = false;

		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		
		if (((beginVertex != null) && (endVertex != null)) || edgeWeight == 0.0)
			result = beginVertex.connect(endVertex, edgeWeight);

		if (result)
			edgeCount++;

		return result;
	} // end addEdge

	
	public boolean addEdge(T begin, T end) 
	{
		
		return addEdge(begin, end, 0);
	} // end addEdge

	
	public boolean hasEdge(T begin, T end) 
	{
		
		boolean found = false;
		
		
		VertexInterface<T> beginVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);
		
		
		if ((beginVertex != null) && (endVertex != null)) 
		{
			
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
			
			
			while (!found && neighbors.hasNext()) 
			{
				
				VertexInterface<T> nextNeighbor = neighbors.next();
				
				
				if (endVertex.equals(nextNeighbor))
					found = true;
				
			} 
		} 
		return found;
	} // end hasEdge

	
	public boolean isEmpty() 
	{
		
		return vertices.isEmpty();
	} // end isEmpty

	
	public void clear() 
	{
		
		vertices.clear();
		edgeCount = 0;
	} // end clear

	
	public int getNumberOfVertices() 
	{
		
		return vertices.getSize();
	} // end getNumberOfVertices

	
	public int getNumberOfEdges() 
	{
		
		return edgeCount;
	} // end getNumberOfEdges

	
	protected void resetVertices() 
	{
		
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		
		
		while (vertexIterator.hasNext()) 
		{
			
			VertexInterface<T> nextVertex = vertexIterator.next();
			
			
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		} 
		
	} // end resetVertices

	
	public StackInterface<T> getTopologicalOrder() 
	{
		
		throw new UnsupportedOperationException("getTopologicalOrder() is not supported " + "by this Directed Graph");
	} // end getTopologicalOrder

	
	public Iterator<VertexInterface<T>> vertexIterator() 
	{
		
		return vertices.getValueIterator();
	} // end vertexIterator

	
	@Override
	public QueueInterface<T> getBreadthFirstTraversal(T origin) 
	{
		
		throw new UnsupportedOperationException("getBreadthFirstTraversal() is not supported " + "by this Directed Graph");
	} // end getBreadthFirstTraversal

	
	@Override
	public QueueInterface<T> getDepthFirstTraversal(T origin) 
	{
		
		throw new UnsupportedOperationException("getDepthFirstTraversal() is not supported " + "by this Directed Graph");
	} // end getDepthFirstTraversal

	
	@Override
	public int getShortestPath(T begin, T end, StackInterface<T> path) 
	{
		
		throw new UnsupportedOperationException("getShortestPath() is not supported " + "by this Directed Graph");
	} // end getShortestPath

	
	public double getCheapestPath(T originVertex, T endVertex, StackInterface<T> path) 
	{

		resetVertices();

		
		boolean done = false;

		
		PriorityQueueInterface<EntryPQ> priorityQueue = new MinHeapPriorityQueue<EntryPQ>();

		VertexInterface<T> originVertex2 = vertices.getValue(originVertex);
		VertexInterface<T> endVertex2 = vertices.getValue(endVertex);

		priorityQueue.add(new EntryPQ(originVertex2, 0, null));

		while (!done && !priorityQueue.isEmpty()) 
		{
			
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();

			if (!frontVertex.isVisited()) 
			{
				
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());

				if (frontVertex.equals(endVertex2))
					done = true;
				else 
				{
					
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();

					
					while (neighbors.hasNext()) {
						
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();

						if (!nextNeighbor.isVisited()) {

							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));

						} 
					} 
				} 
			} 
		} 

		// traversal ends, construct cheapest path
		double pathCost = endVertex2.getCost();
		path.push(endVertex2.getLabel());

		
		VertexInterface<T> vertex = endVertex2;
		
		
		while (vertex.hasPredecessor()) 
		{
			
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
		} 

		return pathCost;
	} // end getCheapestPath

	
	
	
	public void showGraph() 
	{
		
		Iterator<VertexInterface<T>> vertexIterator = vertexIterator();
		
		
		while (vertexIterator.hasNext()) 
		{
			
			VertexInterface<T> vertex = vertexIterator.next();
			
			
			System.out.print(vertex.getLabel() + ": ");
			
			
			Iterator<VertexInterface<T>> neighborIterator = vertex.getNeighborIterator();
			Iterator<Double> edgeWeights = vertex.getWeightIterator();
			
			
			while (neighborIterator.hasNext()) 
			{

				VertexInterface<T> neighbor = neighborIterator.next();
				System.out.print(vertex.getLabel() + " --> " + neighbor.getLabel() + " (" + edgeWeights.next() + "):  ");
			}
			
			System.out.println();
		}
		
	} // end showGraph

	
	
	
	public class EntryPQ implements Comparable<EntryPQ> 
	{

		private VertexInterface<T> vertex;
		private double cost;
		private VertexInterface<T> predecessor;

		public EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> predecessor) 
		{
			
			this.vertex = vertex;
			this.cost = cost;
			this.predecessor = predecessor;
		}

		public VertexInterface<T> getVertex() 
		{
			
			return vertex;
		}

		public double getCost() 
		{
			
			return cost;
		}

		public VertexInterface<T> getPredecessor() 
		{
			
			return predecessor;
		}

		public void setCost(double cost) 
		{
			
			this.cost = cost;
		}

		public void setPredecessor(VertexInterface<T> predecessor) 
		{
			
			this.predecessor = predecessor;
		}

		@Override
		public int compareTo(EntryPQ other) 
		{
			
			if (cost < other.cost) 
			{
				
				return -1;
				
			} else if (cost > other.cost) 
			{
				
				return 1;
				
			} else {
				
				return 0;
				
			}
			
		}
		
	} // end EntryPQ

}