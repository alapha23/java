#ifndef __COMPLEX_CPP
#define __COMPLEX_CPP
#include "Complex.h"

using namespace std;

Complex::Complex(double real, double imag)
{this->real = real;this->imag = imag;};
double Complex::getReal(){double ret = this->real; return ret;};
double Complex::getImag(){double ret = this->imag; return ret;};
void Complex::setReal(double real){this->real = real;};
void Complex::setImag(double imag){this->imag = imag;};
Complex Complex::add(Complex c) 
{
	Complex ret (this->getReal()+c.getReal(), this->getImag()+c.getImag());
	return ret;
};
Complex Complex::multiply(Complex c)
{
	double newreal, newimag;
	newreal = this->getReal() * c.getReal() - this->getImag() * c.getImag();
	newimag = this->getImag() * c.getReal() + this->getReal() * c.getImag();
	Complex ret (newreal, newimag);
	return ret;
;};
#endif
