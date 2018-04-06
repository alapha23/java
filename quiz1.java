import java.util.*;
import java.lang.*;

class quiz1
{
	static void addDigits(int number)
	{
		int length = (int)Math.log10((double)number);
		int sum = 0;		

		for(int i=length; i>=0; i--)
		{
			sum =sum+ Integer.toString(number).charAt(i) - '0';
			System.out.println( "DEBUG"+(Integer.toString(number).charAt(i) - '0'));
		}
		System.out.println("Sum= "+sum);	//

	}


	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);	
		int number = sc.nextInt();

		addDigits(number);
	}

}
