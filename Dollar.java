import java.util.*;

class Dollar{

	static void specialB(int key)
	{
		switch(key)
		{
			case 3:
				emitLine(1,3);
				emitLineB(3,3);
				System.out.println("@$@");
				emitLineB(3,3);
				emitLine(1,3);
				break;
			case 2:
				emitLine(2,2);
				emitLine(2,2);
				emitLine(2,2);
				break;
			case 1:
				emitLine(1,1);
				emitLine(1,1);
				emitLine(1,1);
				break;
		}
	}
	

	static void special(int key)
	{
		switch(key)
		{
			case 3:
				emitLine(1,3);
				emitLine(3,3);
				emitLine(1,3);
				emitLine(3,3);
				emitLine(1,3);
				break;
			case 2:
				emitLine(2,2);
				emitLine(2,2);
				emitLine(2,2);
				break;
			case 1:
				emitLine(1,1);
				emitLine(1,1);
				emitLine(1,1);
				break;
		}
	}
	
	static void emitLineB(int require, int key)
	{
		/* align required under max length */
		int space=0;
		int dollar=require;
		/* odd */
		space = key/2 - require/2;

		while(space != 0)
		{
			System.out.printf("%c", 0x20);
			space--;
		}
		System.out.print("$");
		dollar--;
		while(dollar>=2)
		{
			System.out.printf("%c", 0x20);
			dollar--;
		}
		if (require != 1)
		System.out.print("$");
	
		System.out.println("");
	}

	static void emitLine(int require, int key)
	{
		/* align required under max length */
		int space=0;
		int dollar=require;
		/* odd */
//		if((max%2) == 1)
			space = key/2 - require/2;
//		else	space = max/2 - require/2;

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
	static void dollarB(int key)
	{
		int upSteps, downSteps;			/* half size: '1 5 9' 5... */
		int odd = key%2 == 1?1:2;	/* 1 if odd, 2 if even */
		double fkey = (double)key;
			
		upSteps = (int)Math.ceil((fkey - (double)odd)/4.0);

		downSteps = odd==1?(int)Math.floor((float)upSteps/(float)2):(int)Math.ceil((float)upSteps/(float)2);

		/* going larger */
		for(int i = 0; i <= upSteps; i++)
		{
			int step;
			
			step = odd + i*4;
			if(step > key)
				step = key;
			emitLineB(step, key);	
		}
		/* going smaller */
		for(int i = upSteps - 1; i > upSteps-downSteps-1; i--)
		{
			int step;

			step = odd + i*4;
			if(step > key)
				step = key;
			if( i == upSteps - downSteps)
			{
				if(key/2 >= step)
				{
					int space = key/2 - step/2 - 1;
					while(space>0)
					{System.out.printf("%c", 0x20);space--;}
					System.out.printf("%c", 0x40);
					System.out.printf("%c", 0x24);step--;
					while(step>1)
					{System.out.printf("%c", 0x20);step--;}
					System.out.printf("$%c\n", 0x40);
					break;
				}
			}	
			emitLineB(step, key);	
		}
		for(int i = upSteps-downSteps+1; i <= upSteps; i++)
		{
			int step;
			
			step = odd + i*4;
			if(step > key)
				step = key;
			emitLineB(step, key);	
		}
		for(int i = upSteps - 1; i >= 0; i--)
		{
			int step;

			step = odd + i*4;
			if(step > key)
				step = key;
			emitLineB(step, key);	
		}

	}


	static void dollarA(int key)
	{
		int upSteps, downSteps;			/* half size: '1 5 9' 5... */
		int odd = key%2 == 1?1:2;	/* 1 if odd, 2 if even */
		double fkey = (double)key;
			
		upSteps = (int)Math.ceil((fkey - (double)odd)/4.0);

		downSteps = odd==1?(int)Math.floor((float)upSteps/(float)2):(int)Math.ceil((float)upSteps/(float)2);

		/* going larger */
		for(int i = 0; i <= upSteps; i++)
		{
			int step;
			
			step = odd + i*4;
			if(step > key)
				step = key;
			emitLine(step, key);	
		}
		for(int i = upSteps - 1; i > upSteps-downSteps-1; i--)
		{
			int step;

			step = odd + i*4;
			if(step > key)
				step = key;
			emitLine(step, key);	
		}
		for(int i = upSteps-downSteps+1; i <= upSteps; i++)
		{
			int step;
			
			step = odd + i*4;
			if(step > key)
				step = key;
			emitLine(step, key);	
		}
		for(int i = upSteps - 1; i >= 0; i--)
		{
			int step;

			step = odd + i*4;
			if(step > key)
				step = key;
			emitLine(step, key);	
		}

	}

	public static void main(String args[])
	{
		int key;
		Scanner sc = new Scanner(System.in);	
		/* Graders will not enter wrong inputs */
		System.out.print("Type the maxium length: ");
		key = sc.nextInt();

		System.out.println("(a)");	
		if(key <= 3)
			special(key);
		else	dollarA(key);

		System.out.println("(b)");	

		if(key <= 3)
			specialB(key);
		else	dollarB(key);

		
	}

}
