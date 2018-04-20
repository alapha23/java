import java.util.*;

interface Shape
{	double getArea();
	double getPerimeter();
}

class lab9_2
{
	public static void main(String []args)
	{}
}

class Circle implements Shape
{
	private double r;
	Circle(double r){this.r = r;}
	Circle(int r){this.r = (double)r;}

	public double getArea(){return Math.PI * this.r * this.r;}
	public double getPerimeter(){return 2 * Math.PI * this.r;}
	
}

class Triangle implements Shape
{
	private double a;
	private double b;
	private double c;
	Triangle(double r, double l, double k){this.a = r;this.b = l; this.c = k;}

	public double getArea(){
		double p = (a+b+c)/2;
		return Math.sqrt(p*(p-a)*(p-b)*(p-c));}
	public double getPerimeter(){return this.a + this.b + this.c;}
	
}
class Rectangle implements Shape
{
	private double r;
	private double l;
	Rectangle(double r, double l){this.r = r;this.l = l;}
	Rectangle(int r, int l){this.r = (double)r;this.l = (double)l;}

	public double getArea(){return this.r * this.l;}
	public double getPerimeter(){return 2 * ( this.l + this.r);}
}
