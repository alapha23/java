import java.util.*;
import java.io.*;

/*
 * stock.txt:  <part id>,<part type>,<part name>,<part price>,<part quantity>
 * part id, numeric values = line number
 * part name, a string with no ','
 * part price, quantity are numeric values
 * <part type>: CPU (C), Motherboard (M), RAM (R), GPU(G) and Power Supply (P)
 * */
class Warehouse 
{
	public static Scanner stock;
	public static Scanner input;

	static void do_exit()
	{
		/* close file descriptors */
		input.close();
	}
	static void open_files() throws FileNotFoundException
	{
		stock = new Scanner(new File("stock.txt"));
		input = new Scanner(new File("input.txt"));
	}
	public static void main(String args[]) 
	{
		String		oneLine;
		int		numStocks = 0;
		retailer	tailer;
		Input		oneInput;

		try{	open_files();	}catch( FileNotFoundException e){}

		while(stock.hasNextLine()){
			numStocks++;
			stock.nextLine();
		}		

		stock.close();
		try{stock = new Scanner(new File("stock.txt"));}catch(Exception e){}

		tailer = new retailer(numStocks);
		while(stock.hasNextLine())
		{
			oneLine = stock.nextLine();
			tailer.addStock(oneLine);
		}
		stock.close();
		// all stock added to tailer object

		while(input.hasNextLine())
		{
			oneLine = input.nextLine();
			oneInput = new Input(oneLine);
			tailer.parseInput(oneInput);
		}
		tailer.do_exit();
		do_exit();
	}
}

class retailer
{
	private int numStocks;
	private int line;
	Stock [] st;
	private int txLine;
	PrintStream newStock;
	PrintStream tx;

	retailer( int numStocks )
	{
		this.numStocks = numStocks;
		this.line = 0;
		this.txLine =0;
		st = new Stock[numStocks];
		try{
			newStock = new PrintStream(new File("stock.txt"));
			tx = new PrintStream(new FileOutputStream("tx.txt", true));
		}catch(Exception e){}
	}
	public void addStock(String str)
	{
		st[line] = new Stock(str);
		this.line++;
	}	
	public void parseInput(Input i)
	{
		if(i.id == 'I')
			imports(i);
		else if(i.id == 'S')	sells(i);
		else if(i.id == 'E')	exchanges(i);
		else System.out.println("Unknow input type");
	}
	public void imports(Input in)
	{
		int index = -1;	// which stock is updating?
//		System.out.printf("LINE:%d, SUM=%d\n", this.line, this.numStocks);
		for(int i = 0; i < this.line; i++)
		{if(st[i].name.equals(in.name)) index=i;}
		if(-1 == index)			
		{
			this.numStocks++;
			st = Arrays.copyOf(st, this.numStocks);
			index = this.line;	// index == 6
			st[line] = new Stock(in, this.line+1);
			this.line++;
			return ;
		}
		if(st[index].price > in.price)
			System.out.println("The part became cheap.");
		else if(st[index].price > in.price)
			System.out.println("The part became expensive.");

		st[index].price = in.price;
		st[index].quantity += in.quantity;
		
		this.txLine++;
		tx.printf("%d,I,%d,%d,%d,%d\n",this.txLine,index+1,st[index].price,in.quantity,in.quantity*in.price);
	}
	public void sells(Input in)
	{
		int index = -1;	// which stock is updating?
		for(int i = 0; i < this.line; i++)
		{if(st[i].name.equals(in.name)) index=i;}
		if(-1 == index)			
			do_exit();
		if(st[index].quantity < in.price)
			do_exit();
		st[index].quantity = st[index].quantity - in.quantity;

		this.txLine++;
		tx.printf("%d,S,%d,%d,%d,%d\n",this.txLine,index+1,st[index].price,in.quantity,in.quantity*st[index].price);

	}
	public void exchanges(Input in)
	{
		int index = -1;	// which stock is updating?
		for(int i = 0; i < this.line; i++)
		{if(st[i].name.equals(in.name)) index=i;}
		if(-1 == index)			
			do_exit();

		if(in.quantity>st[index].quantity)
		{
			System.out.println("The part partially exchanged.");
			this.txLine++;
			tx.printf("%d,E,%d,%d,%d,%d\n",this.txLine,index+1,st[index].price,st[index].quantity,st[index].quantity*st[index].price);
		}else
		{
			this.txLine++;
			tx.printf("%d,E,%d,%d,%d,%d\n",this.txLine,index+1,st[index].price,in.quantity,in.quantity*st[index].price);

		}

	}

	public void showStock()
	{
		for(int i = 0; i < this.line; i++)
		{
			st[i].printStock();

		}
	}
	public void do_exit()
	{
		for(int i = 0; i < this.line; i++)
		{
			newStock.printf("%d,%c,%s,%d,%d\n", st[i].id, st[i].type, st[i].name, st[i].price, st[i].quantity);
		}
		{java.lang.System.exit(0);}
	}
}

class Input{
	public char type;

	private int iter;

	public char id;		// I: import, S: sells, E: exchanges
	public String name;
	public int price;
	public int quantity;

	Input(String input)
	{
		this.iter = 0;
		this.id = (getToken(input, this.iter)).charAt(0);
		this.iter++;
		this.type = getToken(input, this.iter).charAt(0);
		this.iter++;
		this.name = getToken(input, this.iter);
		if(this.id == 'I')
		{
			this.iter++;
			this.price = Integer.parseInt(getToken(input, this.iter));
		}
		this.iter++;
		this.quantity = Integer.parseInt(getToken(input, this.iter));
	}

	public void printStock()
	{
		System.out.printf("%c, %s,%d,%d\n", this.type, this.name, this.price, this.quantity);
	}
	private String getToken(String s, int iter)
	{
		int i = 0;
		int beginIndex=0;
		int endIndex=0;
		char c = s.charAt(i);

		for(int j=iter;j>0;j--)
		{
			while(c != ',' && c!= '\n' )
			{
				i++;
				if(i>=s.length())
				{System.out.println("Wrong Input");java.lang.System.exit(0);}
				c = s.charAt(i);
			}

			i++;
			c = s.charAt(i);
			beginIndex=i;
		}
		while(c != ',' && c!= '\n'   )
		{
			i++;
			if(i == s.length())
				break;
			c = s.charAt(i);
		}
		endIndex = i;
		return s.substring(beginIndex, endIndex);
	}
}

class Stock{
	private int iter;

	public String name;
	public int price;
	public int quantity;
	public int id;
	public char type;
	Stock(String input)
	{
		this.iter = 0;
		this.id = Integer.parseInt(getToken(input, this.iter));
		this.iter++;
		this.type = getToken(input, this.iter).charAt(0);
		this.iter++;
		this.name = getToken(input, this.iter);
		this.iter++;
		this.price = Integer.parseInt(getToken(input, this.iter));
		this.iter++;
		this.quantity = Integer.parseInt(getToken(input, this.iter));
	}
	Stock(Input in, int id)
	{
		this.id = id;
		this.type = in.type;
		this.name = in.name;
		this.price = in.price;
		this.quantity = in.quantity;
	}

	public void printStock()
	{
		System.out.printf("%d,%c, %s,%d,%d\n", this.id, this.type, this.name, this.price, this.quantity);
	}
	private String getToken(String s, int iter)
	{
		int i = 0;
		int beginIndex=0;
		int endIndex=0;
		char c = s.charAt(i);

		for(int j=iter;j>0;j--)
		{
			while(c != ',' && c!= '\n' )
			{
				i++;
				c = s.charAt(i);
			}

			i++;
			c = s.charAt(i);
			beginIndex=i;
		}
		while(c != ',' && c!= '\n'   )
		{
			i++;
			if(i == s.length())
				break;
			c = s.charAt(i);
		}
		endIndex = i;
		return s.substring(beginIndex, endIndex);
	}
}
