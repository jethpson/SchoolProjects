/*Author: Jacob Thompson
**CS2560-01, Fall 2023
**Lab Assignment #4
**Description:
**Do lab #3, but use a stack!
*/


// Triangle.h
#ifndef TRIANGLE_H
#define TRIANGLE_H

class Triangle {

 public:

 Triangle (float base, float height);
 float area ();
 float getBase();
 float getHeight();

 private:

 float base_;
 float height_;

};

#endif /* TRIANGLE_H */
