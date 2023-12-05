/*Author: Jacob Thompson
**CS2560-01, Fall 2023
**Lab Assignment #4
**Description:
**Do lab #3, but use a stack!
*/

#include "Triangle.h"


Triangle::Triangle(float base, float height) {

	base_ = base;
	height_ = height;

}


float Triangle::area() {

	return 0.5 * base_ * height_;
}

float Triangle::getBase() {
	return base_;
}

float Triangle::getHeight(){
	return height_;
}
