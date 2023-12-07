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

template <class T>
class ListNode 
{

public:

    ListNode();
    ListNode(T* value, ListNode<T>* next);
    ~ListNode();
    T* getValue() const;
    void setValue(T* value);
    ListNode<T>* getNext() const;
    void setNext(ListNode<T>* next);

private:

    T* value_;
    ListNode<T>* next_;
};


template <class T>
ListNode<T>::ListNode() : value_(nullptr), next_(nullptr) {}

template <class T>
ListNode<T>::ListNode(T* value, ListNode<T>* next) : value_(value), next_(next) {}

template <class T>
ListNode<T>::~ListNode() 
{

    delete this->value_;
    this->next_ = nullptr;
}

template <class T>
T* ListNode<T>::getValue() const 
{

    return this->value_;
}

template <class T>
void ListNode<T>::setValue(T* value) 
{

    this->value_ = value;
}

template <class T>
ListNode<T>* ListNode<T>::getNext() const 
{

    return this->next_;
}

template <class T>
void ListNode<T>::setNext(ListNode<T>* next) 
{

    this->next_ = next;
}