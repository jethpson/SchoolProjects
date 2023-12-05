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

public interface GraphInterface<T> extends BasicGraphInterface<T>,
GraphAlgorithmsInterface<T>
{
} // end GraphInterface