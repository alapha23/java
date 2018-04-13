import java.util.*;

class lab7
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		Complex complexA, complexB, result;
		int realA, virtualA;
		int realB, virtualB;

		System.out.println("Input the real part of first number:");

		realA = sc.nextInt();
		System.out.println("Input the imaginary part of first number:");

		virtualA = sc.nextInt();

		System.out.println("Input the real part of second number:");

		realB = sc.nextInt();
		System.out.println("Input the imaginary part of second number:");

		virtualB = sc.nextInt();


		complexA = new Complex(realA, virtualA);
		complexB = new Complex(realB, virtualB);

		result = complexA.addComplex(complexA, complexB);
		System.out.printf("Sum:%d+%di\n", result.getReal(),result.getVirtual());
		result = complexA.minusComplex(complexA, complexB);
		System.out.printf("Difference:%d+%di\n", result.getReal(),result.getVirtual());
	}

}

class Complex
{
	private int real;
	private int virtual;

	Complex(int real, int virtual)
	{ this.real = real; this.virtual = virtual; }

	public Complex addComplex(Complex a, Complex b)
	{
		int real, virtual;
		real = a.getReal() + b.getReal();
		virtual = a.getVirtual() + b.getVirtual();
		Complex ret = new Complex(real, virtual);

		return ret;
	}
	public Complex minusComplex(Complex a, Complex b)
	{
		int real, virtual;
		real = a.getReal() - b.getReal();
		virtual = a.getVirtual() - b.getVirtual();
		Complex ret = new Complex(real, virtual);

		return ret;
	}
	
	public int getReal()
	{ return this.real; }
	public int getVirtual()
	{ return this.virtual; }
}
