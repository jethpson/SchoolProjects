/*Author: Jacob Thompson
**CS2560-01, Fall 2023
**Lab Assignment #4
**Description:
**Do lab #3, but use a stack!
*/

#include "StackNode.h"

template <class T>
class Stack {
  public:
    Stack ();
    ~Stack ();

    bool push (const T& value);
    bool pop (T& value);
    int length () const;

  private:
    StackNode<T>* top_;
    int length_;
};


template <class T>
Stack<T>::Stack () : top_(NULL), length_(0) {
}

template <class T>
Stack<T>::~Stack () {
   StackNode<T>* temp = this->top_;
   while (temp != NULL) {
      this->top_ = this->top_->getNext();
      delete temp;
      temp = this->top_;
   }
}

template <class T>
bool Stack<T>::push (const T& value) {
   StackNode<T>* node = new StackNode<T> (value, this->top_);
   this->top_ = node;
   ++this->length_;
   return true;
}

template <class T>
bool Stack<T>::pop (T& value) {
   if (this->top_ == NULL) return false;
   StackNode<T>* node = this->top_;
   this->top_ = this->top_->getNext();
   value = node->getValue();
   delete node;
   --this->length_;
   return true;
}

template <class T>
int Stack<T>::length() const {
   return this->length_;
}
