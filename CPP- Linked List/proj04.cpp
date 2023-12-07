/* Author: Jacob Thompson
** CS2560-01, Fall 2023
** Project #4
** 
** Description:
** Redoing Project #3 with an abstract base class and LinkedList.The project defines classes for books (both hardcover and paperback), 
** a warehouse class, and includes templates for nodes and linked lists. It is able to read input from a File and list and find content with
** a console interface.
**
** The program is run with command line arguements: ./proj04 books.dat list
**											    or:	./proj04 books.dat find 0-201-71089-7 		
**
** Objectives: inheritance, pointers, linked list, template class associations, and operator overloading.
*/

#include "Book.h"
#include "Warehouse.h"
#include <fstream>

int main(int argc, char* argv[])
{

	Warehouse warehouse;				
	

	if (argv[1] != NULL)
	{

		ifstream inputFile(argv[1]);	//Opens File Stream with first argument.

		if (!inputFile.is_open())
		{

			cerr << "Error: Unable to open file 'books.dat'." << endl;
			exit(1);
		}

		inputFile >> warehouse;			//Operator overloading takes inputFile.

		inputFile.close();
	}


	if (argc == 3)
	{

		if (string(argv[2]) == "list")
		{

			cout << warehouse;		//Operator overloading to print formated file outputs.
		}
		else
		{
			cout << "./proj02 <input file> find <id>\n./proj02 <input file> list" << endl;
			exit(0);
		}
	}
	else if (argc == 4)
	{

		if (string(argv[2]) == "find")
		{

			Book* foundBook = warehouse.find(argv[3]);

			if (foundBook == nullptr) {}
			else {
				cout << "ISBN: " << argv[3] << " -- FOUND\n";
				cout << "Title: " << foundBook->getTitle() << "\n";
				for (int i = 0; i < foundBook->getAuthorCount(); ++i)
				{

					cout << "Author: " << foundBook->getAuthors()[i] << "\n";
				}
				cout << "Publisher: " << foundBook->getPublisher() << "\n";
				cout << "Year: " << foundBook->getYearPublish() << "\n";
				cout << "Cover: " << foundBook->getCoverType() << "\n";
				cout << "Price: $" << foundBook->getPrice() << "\n";
				cout << "ISBN: " << foundBook->getISBN() << "\n";
				cout << "Copies: " << foundBook->getCopies() << "\n";
			}
		}
		else
		{
			cout << "./proj02 <input file> find <id>\n./proj02 <input file> list" << endl;
			exit(0);
		}
	}
	else
	{

		cout << "./proj02 <input file> find <id>\n./proj02 <input file> list" << endl;
		exit(0);
	}

	warehouse.cleanUp();				//Clears the linked list of all items, reallocating dynamic data. 

	return 0;
	
}
