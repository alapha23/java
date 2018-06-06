#include <algorithm>
#include <iostream>
#include <vector>
#include "cpScalar.hpp"
#include "cpVector.hpp"

int main(int argc, char *argv[])
{
	cpScalar s(3);
	cpScalar t(4);
	cpScalar k(3.9);
	std::cout << s << std::endl; //Should print 3.
	std::cout << s + t - k << std::endl; //Should print 7.
	/* Generate an cpScalar array with the vector. */
	std::vector<cpScalar> vv;

	vv.push_back(s);
	vv.push_back(t);
	vv.push_back(k);
	cpScalar a[3];

	std::copy(vv.begin(), vv.end(), a);
	cpVector v(a, 3);
	std::cout << v - v << std::endl;
	std::cout << s * v << std::endl; //Should print [9, 12].
	std::cout << v + t << std::endl; //Should print [7, 8].

	return 0;
}
