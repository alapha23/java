import java.io.*;
import java.util.*;

class lab6
{

	public static void main(String args[]) throws FileNotFoundException 
	{
		PrintStream f1 = new PrintStream(new File("out1.txt"));
		PrintStream f2 = new PrintStream(new File("out2.txt"));

		int arr[] = new int[20];
		for(int i=0; i<20; i++)
		{
			arr[i] = i*5+5;
		}
	
		for(int i=0; i<20; i++)
		{
			f1.println(arr[i]);
		}
		for(int i=0; i<20; i++)
		{
			f2.print(arr[i]+", ");
		}
		f2.println("");
	}

}
