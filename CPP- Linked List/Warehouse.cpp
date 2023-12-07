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
** Objectives: inheritance, pointers, linked list, template class associations, file input/output, and operator overloading.
*/

#include "Warehouse.h"
#include "Book.h"
#include "HardcoverBook.h" 
#include "PaperbackBook.h"


Warehouse::Warehouse() : myList() {}			//Initilizes the Linked List. 


istream& operator>>(istream& is, Warehouse& warehouse)
{
	if(is.peek() != EOF)
	while (is.peek() != EOF)
	warehouse.createBook_(is);			//Reads file and calls createBook_ function.
	else
	{
		cout << "Empty File.";
	}

	return is;
}

ostream& operator<<(ostream& os, const Warehouse& warehouse)
{

    warehouse.list();                           //Call's list function.
 
	return os;
}


Book* Warehouse::find(string isbn) const
{

	ListNode<Book>* current = myList.getHead();	//Create a temp pointer.

	while(current != nullptr)					//Traverses Linked List until item is found.
	{		

		if (current->getValue()->getISBN() == isbn) 
		{
			
			return current->getValue();
		}

		current = current->getNext();
	}

    cout << "ISBN: " << isbn << " -- NOT FOUND\n";
    

    return nullptr;
}


void Warehouse::list() const
{
	
	ListNode<Book>* current = myList.getHead();	//Traverses Linked List of Books,
	while (current != nullptr)					//Sending them through operation overloading
	{											//to a method that prints the data formated.

		cout << *current->getValue();			
		current = current->getNext();
	}
}



Book* Warehouse::createBook_(istream& is)
{

		if (is.peek() != EOF)
		{

			string line;
			string authors[Book::MAX_AUTHORS];
			string title, publisher, isbn;
			short yearPublish;
			float price;
			long copies;
			bool hardcover;
			int authorCount;

			getline(is, title);
			getline(is, line);

			try
			{

				authorCount = stoi(line);


				for (int i = 0; i < authorCount; ++i)
				{

					if (i < Book::MAX_AUTHORS)
					{

						getline(is, authors[i]);
					}
					else
					{

						cout << "Error: AuthorCount Excedes Limit.\n";
						exit(1);
					}
				}
			}
			catch (const exception& e) { cout << "improper Author Format\n" << e.what(); exit(1); }

			getline(is, publisher);

			getline(is, line); try { yearPublish = static_cast<short>(stoi(line)); }
			catch (const exception&) { cout << "improper yearPublish Format\n"; exit(1); }

			getline(is, line); try { hardcover = stoi(line); }
			catch (const exception&) { cout << "improper hardcover Format\n"; exit(1); }

			getline(is, line); try { price = stof(line); }
			catch (const exception&) { cout << "improper price Format\n"; exit(1); }

			getline(is, isbn);
			getline(is, line); try { copies = stol(line); }
			catch (const exception&) { cout << "improper copies Format\n"; exit(1); }
		
			

			if (hardcover)			//Creates either a hardcopy book or paperback book depending on the data read.
			{

				Book* myBook = new HardcoverBook(title, authors, authorCount, publisher,
					yearPublish, hardcover, price, isbn, copies);

				myList.insert(myBook);
				return myBook;
			}
			else
			{

				Book* myBook = new PaperbackBook(title, authors, authorCount, publisher,
					yearPublish, hardcover, price, isbn, copies);

				myList.insert(myBook);
				return myBook;
			}

			
		}

		return nullptr;				
}

void Warehouse::cleanUp()			//Removes LinkedList items while freeing the space up.
{

	while (myList.length() > 0) 
	{

		Book* book = nullptr;

		if (myList.remove(book)) 
		{

			delete book;
		}
	}
}

Warehouse::~Warehouse() {}
