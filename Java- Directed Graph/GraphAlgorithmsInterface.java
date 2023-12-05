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

/** An interface of methods that process an existing graph. */
public interface GraphAlgorithmsInterface<T>
{
/** Performs a breadth-first traversal of this graph.
@param origin An object that labels the origin vertex of the traversal.
@return A queue of labels of the vertices in the traversal, with
the label of the origin vertex at the queue's front. */
public QueueInterface<T> getBreadthFirstTraversal(T origin);

/** Performs a depth-first traversal of this graph.
@param origin An object that labels the origin vertex of the traversal.
@return A queue of labels of the vertices in the traversal, with
the label of the origin vertex at the queue's front. */
public QueueInterface<T> getDepthFirstTraversal(T origin);

/** Performs a topological sort of the vertices in a graph without cycles.
@return A stack of vertex labels in topological order, beginning
with the stack's top. */
public StackInterface<T> getTopologicalOrder();

/** Finds the shortest-length path between two given vertices in this graph.
@param begin An object that labels the path's origin vertex.
@param end An object that labels the path's destination vertex.
@param path A stack of labels that is empty initially;
at the completion of the method, this stack contains
the labels of the vertices along the shortest path;
the label of the origin vertex is at the top, and
the label of the destination vertex is at the bottom.
@return The length of the shortest path. */
public int getShortestPath(T begin, T end, StackInterface<T> path);

/** Finds the least-cost path between two given vertices in this graph.
@param begin An object that labels the path's origin vertex.
@param end An object that labels the path's destination vertex.
@param path A stack of labels that is empty initially;
at the completion of the method, this stack contains
the labels of the vertices along the cheapest path;
the label of the origin vertex is at the top, and
the label of the destination vertex is at the bottom.
@return The cost of the cheapest path. */
public double getCheapestPath(T begin, T end, StackInterface<T> path);
} // end GraphAlgorithmsInterface