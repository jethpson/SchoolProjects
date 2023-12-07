#pragma once
#include "Book.h"

class HardcoverBook : public Book {
public:
    HardcoverBook();
    HardcoverBook(string title, string authors[], int authorCount, string publisher,
        short yearPublish, bool hardcover, float price,
        string isbn, long copies);

    string getCoverType() const override;
};