// @file Book.h
#ifndef BOOK_H
#define BOOK_H
#include <iostream>
#include <string>

using namespace std;

class Book {

	/**
	* @param os the output stream
	* @param book the book object reference * @return the output stream
	*/
	friend ostream& operator << (ostream& os, const Book& book);


public:

	static const int MAX_AUTHORS = 35;

	virtual ~Book();
	virtual string getCoverType() const = 0;


	Book();

	Book(string title, string authors[], int authorCount, string publisher,
		short yearPublish, bool hardcover, float price,
		string isbn, long copies);

	void setTitle(string title); string getTitle() const;

	void setAuthors(std::string authors[], int authorCount);
	const std::string* getAuthors() const;

	void setAuthorCount(int authorCount);
	int getAuthorCount() const;

	void setPublisher(std::string publisher); std::string getPublisher() const;

	void setYearPublish(short yearPublish); short getYearPublish() const;

	void setHardcover(bool hardcover); bool isHardcover() const;

	void setPrice(float price); float getPrice() const;

	void setISBN(std::string isbn); std::string getISBN() const;

	void setCopies(long copies); long getCopies() const;

	void setNext(Book* next); Book* getNext() const;


private:
	string title;
	string authors[Book::MAX_AUTHORS];
	int authorCount;
	std::string publisher;
	short yearPublish;
	bool hardcover;
	float price;
	std::string isbn;
	long copies;

	Book* next;
};


#endif /* BOOK_H */