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
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface<T>
{
	
private T label;
private SinglyLinkedList<Edge> edgeList; // Edges to neighbors
private boolean visited; // True if visited
private VertexInterface<T> previousVertex; // On path to this vertex
private double cost; // Of path to this vertex

public Vertex(T vertexLabel)
{
	
	label = vertexLabel;
	edgeList = new SinglyLinkedList<>();
	visited = false;
	previousVertex = null;
	cost = 0;
} // end constructor


public boolean connect(VertexInterface<T> endVertex, double edgeWeight) 
{
	
	boolean result = false;
	int Position = 0;
	
	String Positionhelper= "";
	String Positionhelper2= "";
	
	Iterator<Double> nextNeighbor2 = getWeightIterator();
	
	
	if (!this.equals(endVertex)) 
	{
		
		Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
		boolean duplicateEdge = false;
		
		
		while (!duplicateEdge && neighbors.hasNext()) 
		{
			
			nextNeighbor2.next();
			
			
			VertexInterface<T> nextNeighbor = neighbors.next();		
			
			
			if (endVertex.equals(nextNeighbor)) 
			{
				
				if (edgeWeight > 0.0)
					duplicateEdge = true;
				else 
				{
					
					Positionhelper = (String) endVertex.getLabel();
					Iterator<VertexInterface<T>> Positionhelperneighbors = getNeighborIterator();
					
					
					while (Positionhelperneighbors.hasNext() && Positionhelper2!=Positionhelper) 
					{
						
						Positionhelper2=(String) Positionhelperneighbors.next().getLabel();
						Position++;
					}
				}
			}
			
		} 
		
		if (!duplicateEdge && edgeWeight != 0.0) 
		{
			
			edgeList.add(new Edge(endVertex, edgeWeight));
			result = true;
		}else if (edgeWeight == 0.0)
		{
			
			edgeList.remove(Position);			
			result = true;
		}
	} 
	
	return result;
} // end connect


public boolean connect(VertexInterface<T> endVertex)
{
	
	return connect(endVertex, 0);
} // end connect


public Iterator<VertexInterface<T>> getNeighborIterator()
{

	return new NeighborIterator();
} // end getNeighborIterator


private class NeighborIterator implements Iterator<VertexInterface<T>>
{
	
	private Iterator<Edge> edges;
	
	private NeighborIterator()
	{
		
		edges = edgeList.getIterator();
	} // end default constructor
	
	public boolean hasNext()
	{
	
		return edges.hasNext();
	} // end hasNext
	
	public VertexInterface<T> next()
	{
	
		VertexInterface<T> nextNeighbor = null;
		if (edges.hasNext())
		{
			
			Edge edgeToNextNeighbor = edges.next();
			nextNeighbor = edgeToNextNeighbor.getEndVertex();
		}
		else
			throw new NoSuchElementException();
		
	return nextNeighbor;
	} // end next
	
	public void remove()
	{
		throw new UnsupportedOperationException("remove() is not supported");
	} 

}// end NeighborIterator


public boolean hasNeighbor()
{
	
	return !edgeList.isEmpty();
} // end hasNeighbor


public VertexInterface<T> getUnvisitedNeighbor()
{
		
	VertexInterface<T> result = null;
	Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
	
	
	while ( neighbors.hasNext() && (result == null) )
	{
		
	VertexInterface<T> nextNeighbor = neighbors.next();
		if (!nextNeighbor.isVisited())
			result = nextNeighbor;
	}
return result;
} // end getUnvisitedNeighbor


public boolean equals(Object other)
{
	
	boolean result;
	
	
	if ((other == null) || (getClass() != other.getClass()))
		result = false;
	else
	{ 
		
		@SuppressWarnings("unchecked")
		Vertex<T> otherVertex = (Vertex<T>)other;
		result = label.equals(otherVertex.label);
	}
	return result;
} // end equals







public T getLabel() 
{
	
    return label;
}

@Override
public void visit() 
{
	
    visited = true;
}


public void unvisit() 
{
	
	visited = false;
}


@Override
public boolean isVisited() 
{
	
	return visited;
}


@Override
public Iterator<Double> getWeightIterator() 
{
	
	SinglyLinkedList<Double> weights = new SinglyLinkedList<>();
	
	
    for (Edge edge : edgeList) 
    {
    	
        weights.add(edge.getWeight());
    }
    
    return weights.iterator();
}


@Override
public void setPredecessor(VertexInterface<T> predecessor) 
{
	
	 previousVertex = predecessor;
}


@Override
public VertexInterface<T> getPredecessor() 
{
	
	 return previousVertex;
}


@Override
public boolean hasPredecessor() 
{
	
	 return previousVertex != null;
}


@Override
public void setCost(double newCost) 
{
	
	 cost = newCost;
}


@Override
public double getCost() 
{
	
	 return cost;
}


	protected class Edge
	{
		
		private VertexInterface<T> vertex; // Vertex at end of edge
		private double weight;
		
		
		protected Edge(VertexInterface<T> endVertex, double edgeWeight)
		{
			
			vertex = endVertex;
			weight = edgeWeight;
		} // end constructor
		
		protected Edge(VertexInterface<T> endVertex)
		{
			
			vertex = endVertex;
			weight = 0;
		} // end constructor
		
		protected VertexInterface<T> getEndVertex()
		{
			
			return vertex;
		} // end getEndVertex
		
		protected double getWeight()
		{
			
			return weight;
		} // end getWeight
		
	} // end Edge

}