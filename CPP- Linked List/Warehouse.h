// @file Warehouse.h
#ifndef WAREHOUSE_H
#define WAREHOUSE_H
#include <iostream>
#include <string>
#include "Book.h"
#include "LinkedList.h"


using namespace std;

class Warehouse {

	/**
	* @param os the output stream
	* @param warehouse the warehouse object reference
	* @return the output stream
	*/
	friend ostream& operator << (ostream& os, const Warehouse& warehouse);
	/**
	* @param is the input stream
	* @param warehouse the warehouse object reference
	* @return the input stream
	*/
	friend istream& operator >> (istream& is, Warehouse& warehouse);


public:

	Warehouse();
	~Warehouse();

	/**
	* @param isbn the ISBN number to search for
	* @return pointer to the Book object, if found. Otherwise, return NULL
	*/
	Book* find(string isbn) const;
	/**
	* Prints the inventory of the Warehouse (i.e. list all the books)
	*/
	void list() const;
	/**
	* Deallocates and removes all items from the Linked List.
	*/
	void cleanUp();

protected:
	// Protected method to create a Book instance based on input data
	Book* createBook_(istream& is);

private:

	List<Book> myList;
		
};
#endif /* WAREHOUSE_H */