#include <iostream>
#include "Complex.h"
#include "Complex.cpp"

int main(int argc, char *argv[])
{
	Complex a(1, 1); //1+1i.
	Complex b(1, 1); //1+1i.
	std::cin >> b;
	Complex addresult = a + b;
	Complex mulresult = (a + b) * (a + b);
	std::cout << addresult << std::endl << mulresult << std::endl;
	return 0;
}
