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

#include "Book.h"
#include "Warehouse.h"

Book::Book()
{						// Initilize the book attributes.
	Book::title = "";
	string authors[MAX_AUTHORS];
	Book::authorCount = 0;
	Book::publisher = "";
	Book::yearPublish = 0;
	Book::hardcover = false;
	Book::price = 0;
	Book::isbn = "";
	Book::copies = 0;
	this->next = nullptr;
}

Book::Book(string title, string authors[], int authorCount, string publisher,
	short yearPublish, bool hardcover, float price,
	string isbn, long copies)
{

	this->title = title;
	this->authorCount = authorCount;
	this->publisher = publisher;
	this->yearPublish = yearPublish;
	this->hardcover = hardcover;
	this->price = price;
	this->isbn = isbn;
	this->copies = copies;

	for (int i = 0; i < authorCount && i < MAX_AUTHORS; ++i)
	{

		this->authors[i] = authors[i];
	}
	this->next = nullptr;

}


ostream& operator << (ostream& os, const Book& book)	//Operation overloading to print book attribes to console.
{
	
		os << "\nTitle: " << book.getTitle() << "\n";
		for (int i = 0; i < book.getAuthorCount(); ++i)
		{

			os << "Authors: " << book.getAuthors()[i] << "\n";
		}
		os << "Publisher: " << book.getPublisher() << "\n";
		os << "Year Published: " << book.getYearPublish() << "\n";
		os << "Cover: " << (book.isHardcover() ? "Digital" : "PaperBack") << "\n";
		os << "Price: $" << book.getPrice() << "\n";
		os << "ISBN: " << book.getISBN() << "\n";
		os << "Copies: " << book.getCopies() << "\n";	

	return os;
}




void Book::setNext(Book* next)
{
	this->next = next;
}
Book* Book::getNext() const { return this->next; }



void Book::setTitle(string title)
{

	this->title = title;
}
string Book::getTitle() const { return this->title; }



void Book::setAuthors(string authors[], int authorCount)
{

	if (authorCount > MAX_AUTHORS)
	{

		cout << "Error: Too many authors for this book.\n";
		exit(1);
	}

	for (int i = 0; i < authorCount; i++)
	{

		this->authors[i] = authors[i];
	}

	this->authorCount = authorCount;
}
const string* Book::getAuthors() const { return authors; }



void Book::setAuthorCount(int authorCount)
{
	this->authorCount = authorCount;
}
int Book::getAuthorCount() const { return authorCount; }



void Book::setPublisher(string publisher)
{
	this->publisher = publisher;
}
string Book::getPublisher() const { return publisher; }



void Book::setYearPublish(short yearPublish)
{
	this->yearPublish = yearPublish;
}
short Book::getYearPublish() const { return yearPublish; }



void Book::setHardcover(bool hardcover)
{
	this->hardcover = hardcover;
}
bool Book::isHardcover() const { return hardcover; }



void Book::setPrice(float price)
{
	this->price = price;
}
float Book::getPrice() const { return price; }



void Book::setISBN(string isbn)
{
	this->isbn = isbn;
}
string Book::getISBN() const { return isbn; }



void Book::setCopies(long copies)
{
	this->copies = copies;
}
long Book::getCopies() const { return copies; }

Book::~Book() {}