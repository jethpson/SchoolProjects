/*Author: Jacob Thompson
**CS2560-01, Fall 2023
**Lab Assignment #4
**Description:
**Do lab #3, but use a stack!
*/

template <class T>
class StackNode {
   public:
      StackNode ();

      StackNode (const T& value, StackNode<T>* next);

      ~StackNode ();

      T getValue () const;
      void setValue (const T& value);
      StackNode<T>* getNext () const;
      void setNext (StackNode<T>* next);

   private:
      T value_;
      StackNode<T>* next_;
};

template <class T>
StackNode<T>::StackNode () : next_(NULL) {
}

template <class T>
StackNode<T>::StackNode (const T& value, StackNode<T>* next) :
   value_(value), next_(next) {
}

template <class T>
StackNode<T>::~StackNode () {
   this->next_ = NULL;
}

template <class T>
T StackNode<T>::getValue () const {
   return this->value_;
}

template <class T>
void StackNode<T>::setValue (const T& value) {
   this->value_ = value;
}

template <class T>
StackNode<T>* StackNode<T>::getNext() const {
   return this->next_;
}

template <class T>
void StackNode<T>::setNext (StackNode<T>* next) {
   this->next_ = next;
}
