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

#include "Node.h"

template <class T>
class List 
{

public:

    List();
    ~List();

    bool insert(T* value);
    bool remove(T* value);
    int length() const;
    ListNode<T>* getHead() const;

private:

    ListNode<T>* head_;
    int length_;
};

template <class T>
List<T>::List() : head_(nullptr), length_(0) {}

template <class T>
List<T>::~List() 
{

    ListNode<T>* current = this->head_;

    while (current != nullptr) 
    {

        ListNode<T>* next = current->getNext();
        delete current;
        current = next;
    }
}

template <class T>
bool List<T>::insert(T* value) 
{

    ListNode<T>* node = new ListNode<T>(value, nullptr);

    if (head_ == nullptr) 
    {
        
        head_ = node;
    }
    else 
    {
       
        ListNode<T>* current = head_;

        while (current->getNext() != nullptr) 
        {

            current = current->getNext();
        }

        current->setNext(node);
    }

    ++length_;
    return true;
}

template <class T>
bool List<T>::remove(T* value) 
{

    if (head_ == nullptr) return false;

    ListNode<T>* node = head_;
    head_ = head_->getNext();
    value = node->getValue();
    delete node;

    --length_;

    if (head_ == nullptr) 
    {

        length_ = 0;
    }

    return true;
}

template <class T>
int List<T>::length() const 
{

    return length_;
}

template <class T>
ListNode<T>* List<T>::getHead() const 
{

    return head_;
}