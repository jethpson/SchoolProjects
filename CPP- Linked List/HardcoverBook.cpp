/* Author: Jacob Thompson
** CS2560-01, Fall 2023
** Project #4
**
** Description:
** Redoing Project #3 with an abstract base class and LinkedList.The project defines classes for books (both hardcover and paperback),
** a warehouse class, and includes templates for nodes and linked lists. It is able to read input from a File and list and find content with
** console interface.
**
** The program is run with command line arguements: ./proj04 books.dat list
**											    or:	./proj04 books.dat find 0-201-71089-7
**
** Objectives: inheritance, pointers, linked list, template class associations, and operator overloading.
*/

#include "HardcoverBook.h"

HardcoverBook::HardcoverBook() : Book() {}

HardcoverBook::HardcoverBook(string title, string authors[], int authorCount, string publisher,
	short yearPublish, bool hardcover, float price,
	string isbn, long copies) : Book(title, authors, authorCount, publisher,
		yearPublish, hardcover, price, isbn, copies) {
	
}

string HardcoverBook::getCoverType() const {
	return "HardCover";
}
