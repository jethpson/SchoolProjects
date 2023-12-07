/*Author: Jacob Thompson
**
**CS2560-01, Fall 2023
**Lab Assignment #4
**
**Use a Stack to iterate through a Triangle class which prints attributes of the class.
*/

#include <iostream>
#include <fstream>
#include "Triangle.h"
#include "Stack.h"

using namespace std;

int main (int argc, char* argv[]) {

Stack<float> stack;

ifstream inputFile("triangles.dat");

if (!inputFile.is_open()){
cout << "could not open file";
return 1;
}

float line, line2;

while (inputFile >> line >> line2) {

stack.push(line);
stack.push(line2);

}

int i = 1;

while (stack.length() != 0){
	stack.pop(line2);
	stack.pop(line);
	Triangle triangle(line, line2);
        float triangleArea = triangle.area();
        std::cout << "Triangle " << i << ": b=" << triangle.getBase() << ", h=" << triangle.getHeight() << ", area=" << triangleArea << endl;
	i++;
}

inputFile.close();
return 0;
}
