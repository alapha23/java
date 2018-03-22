import java.util.*;

class Dollar{
	
	static void emit(int require, int max)
	{
		/* align required under max length */
		int space=0;
		int dollar=require;
		if((max%2) == 1)
			space = max/2 - require/2;

		while(space != 0)
		{
			System.out.printf("%c", 0x20);
			space--;
		}
		while(dollar!=0)
		{
			System.out.print("$");
			dollar--;
		}
		System.out.println("");
	}
	static void dollarA(int key)
	{
		int hArrSize;	/* half size: '1 5 9' 5... */
			
		hArrsize = (key-1)/4+2;
	}

	public static void main(String args[])
	{
		int key;
		Scanner sc = new Scanner(System.in);	
		/* Graders will not enter wrong inputs */
		System.out.print("Type the maxium length: ");
		key = sc.nextInt();

		System.out.println("(a)");	
		dollarA(key);
	
	}

}
