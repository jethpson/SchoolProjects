#pragma once
#include "Book.h"

class PaperbackBook : public Book {
public:
    PaperbackBook();
    PaperbackBook(string title, string authors[], int authorCount, string publisher,
        short yearPublish, bool hardcover, float price,
        string isbn, long copies);

    string getCoverType() const override;
};