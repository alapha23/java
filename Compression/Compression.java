import java.util.*;
import java.io.*;

/*
 * The idea is to assign variable-length codes to input characters, lengths of the 
 * assigned codes are based on the frequencies of corresponding characters. The most 
 * frequent character gets the smallest code and the least frequent character gets 
 * the largest code.
 *
 * */

class Compression
{
	public static Dictionary dicObject[];
	public static Scanner	dicSc;
	public static Scanner	input;

	public static void main(String args[])
	{
		int numIndex = 0;
		String oneLine;
		Huff hu = null;
		Tunstall tu = null;

		try{dicSc = new Scanner(new File("dictionary.txt"));}catch(Exception e){java.lang.System.exit(1);}
		while(dicSc.hasNextLine())
		{numIndex++;dicSc.nextLine();}
		dicSc.close();

		try{dicSc = new Scanner(new File("dictionary.txt"));}catch(Exception e){java.lang.System.exit(1);}
		dicObject = new Dictionary[numIndex];

		numIndex = 0;
		while(dicSc.hasNextLine())
		{oneLine = dicSc.nextLine(); dicObject[numIndex] = new Dictionary(oneLine); numIndex++;}

		// read input
		try{input = new Scanner(new File("input.txt"));}catch(Exception e){java.lang.System.exit(1);}
			
		oneLine = input.nextLine();
		if(oneLine.charAt(0)=='H')
			hu = new Huff(oneLine);
		else tu = new Tunstall(oneLine);
		
		if(hu != null)
		{
			if(oneLine.charAt(2)=='C')
				hu.Compress(dicObject);
			else hu.Decode(dicObject);
		//	hu.display(oneLine.charAt(2));
			hu.close(oneLine.charAt(2));
		}
		else
		{
			if(oneLine.charAt(2)=='C')
				tu.Compress(dicObject);
			else{ tu.Decode(dicObject);}
		//	tu.display(oneLine.charAt(2));
			tu.close(oneLine.charAt(2));
		}
		dicSc.close();
		input.close();
	}
}
class Huff
{
	public char type;	// C: Compression, D: Decode
	public String	str;
	public String	numStr;		// in decode mode we have numbers
	public int	num;

	public PrintStream ps;

	Huff(String in)
	{
		String []parts = in.split(",");	
		if(parts.length<3){System.out.println("Input deprecated");java.lang.System.exit(1);}
		type = parts[1].charAt(0);
		if(type == 'C')
		{str = parts[2];numStr=null;num=-1;}
		else if(type=='D')
		{
			numStr = parts[2];
			if(numStr.length()<=10)
				num=Integer.parseInt(numStr);
			str=null;
		}
		else{System.out.println("Input deprecated");java.lang.System.exit(1);}

		try{ps = new PrintStream(new File("output.txt"));}
		catch(Exception e){}
	}
	public void Decode(Dictionary []dick)
	{
		/* Turn 0101 into ABC */
		int size = dick.length;
		int index = 0;
		Dictionary oneDick = null;
		char c;
		String pattern = "";
		str = "";

		while(index < this.numStr.length())
		{		
			int flag = 0;	
			c = numStr.charAt(index);
			pattern += c;

			for(int i = 0; i < size; i++)
			{
				oneDick = dick[i];
				if(oneDick.numStr.equals(pattern))
				{flag = 1; pattern = "";break;}

			}
			if(flag==0) {index++;continue;}
			if(oneDick==null){System.out.println("Dick deprecated");java.lang.System.exit(1);}			
			this.str += oneDick.c;
			index++;
		}
	}
	public void Compress(Dictionary []dick)
	{
		/* Turn ABC into 0101 */
		int size = dick.length;
		int index = 0;
		Dictionary oneDick = null;
		char c;
		numStr = "";

		while(index < this.str.length())
		{
			c = str.charAt(index);
			for(int i = 0; i < size; i++)
			{
				oneDick = dick[i];
				if(oneDick.c.charAt(0) == c)
				{break;}
			}
			if(oneDick==null){System.out.println("Dick deprecated");java.lang.System.exit(1);}
			numStr += oneDick.numStr;
			index++;
		}
	}
	public void display()
	{if(str == null)System.out.println(numStr);else System.out.println(str);}
	public void display(char type)
	{if(type == 'C')System.out.println(numStr);else System.out.println(str);}
	public void close(char type)
	{if(type == 'C')ps.println(numStr);else ps.println(str);ps.close();}

}

class Dictionary
{
	public String c;
	public String numStr;
	public int num;

	Dictionary(String in)
	{
		String []parts = in.split(",");
		this.c = parts[0];
		this.numStr = parts[1];
		this.num = Integer.parseInt(numStr);
	}
}
class Tunstall
{
	public char type;	// C: Compression, D: Decode
	public String	str;
	public String	numStr;		// in decode mode we have numbers
	public int	num;

	public PrintStream ps;

	Tunstall(String in)
	{
		String []parts = in.split(",");	
		if(parts.length<3){System.out.println("Input deprecated");java.lang.System.exit(1);}
		type = parts[1].charAt(0);
		if(type == 'C')
		{str = parts[2];numStr=null;num=-1;}
		else if(type=='D')
		{
			numStr = parts[2];
			if(numStr.length()<=10)
				num=Integer.parseInt(numStr);
			str=null;
		}
		else{System.out.println("Input deprecated");java.lang.System.exit(1);}

		try{ps = new PrintStream(new File("output.txt"));}
		catch(Exception e){}
	}
	public void Decode(Dictionary []dick)
	{
		/* Turn 0101 into ABC */
		int size = dick.length;
		int index = 0;
		int entryLen = dick[0].numStr.length();	// we suppose entry length fixed
		Dictionary oneDick = null;
		char c;
		String pattern = "";
		str = "";

		while(index < this.numStr.length())
		{		
			pattern = numStr.substring(index, index+entryLen);

			for(int i = 0; i < size; i++)
			{
				oneDick = dick[i];
				if(oneDick.numStr.equals(pattern))
				{ pattern = ""; break;}

			}
			if(oneDick==null){System.out.println("Dick deprecated");java.lang.System.exit(1);}			
			this.str += oneDick.c;
			index += 3;
		}
	}
	public void Compress(Dictionary []dick)
	{
		/* Turn ABC into 0101 */
		int size = dick.length;
		int index = 0;
		Dictionary oneDick = null;
		char c;
		String pattern = "";
		numStr = "";

		while(index < this.str.length())
		{
			int flag = 0;
			c = str.charAt(index);
			pattern += c;
			for(int i = 0; i < size; i++)
			{
				oneDick = dick[i];
				if(oneDick.c.equals(pattern))
				{flag = 1;pattern="";break;}
			}
			while(flag == 0)
			{
				index++;
				c = str.charAt(index);
				pattern += c;
				for(int i = 0; i < size; i++)
				{
					oneDick = dick[i];
					if(oneDick.c.equals(pattern))
					{flag = 1;pattern="";break;}
				}
			}
			if(oneDick==null){System.out.println("Dick deprecated");java.lang.System.exit(1);}
			numStr += oneDick.numStr;
			index++;
		}
	}
	public void display()
	{if(str == null)System.out.println(numStr);else System.out.println(str);}
	public void display(char type)
	{if(type == 'C')System.out.println(numStr);else System.out.println(str);}
	public void close(char type)
	{if(type == 'C')ps.println(numStr);else ps.println(str);ps.close();}



}
