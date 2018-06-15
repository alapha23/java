#ifndef COMPLEX_H
#define COMPLEX_H

#include <iostream>

class Complex {
private:
	double real;
	double imag;
public:
	Complex(double real, double imag);
	double getReal();
	double getImag();
	void setReal(double real);
	void setImag(double imag);
	Complex add(Complex c);
	Complex multiply(Complex c);
	friend Complex operator+(Complex c1, Complex c2) 
	{		
		double a, b;
		a = c1.getReal() + c2.getReal();
		b = c1.getImag() + c2.getImag();

		Complex ret (a, b);
		return ret;
	}
	friend Complex operator*(Complex c1, Complex c2)
	{
		Complex ret = c1.multiply(c2);
		return ret;
	}
	friend std::ostream& operator<<(std::ostream& out, Complex &o)
	{
		double real = o.getReal();
		double imag = o.getImag();
		out << real << " " << imag;
		return out;
	}
	friend std::istream& operator>>(std::istream& input, Complex &o)
	{
		double real, imag;
		input >> real;
		input >> imag;
		o.setReal(real);
		o.setImag(imag);
		return input;
	}
};

#include "Complex.cpp"
#endif
