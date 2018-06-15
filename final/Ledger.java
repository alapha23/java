public class Ledger
{
	boolean txtype;
	int unitprice;
	int amount;
	Ledger next;
	int	id;
	int sold_out;
	/* You may modify if you want. */
	public Ledger(boolean t, int u, int a, int id)
	{
		this.txtype = t;
		this.unitprice = u;
		this.amount = a;
		this.next = null;
		this.id = id;
		this.sold_out=0;
	}

	/* Add more. */
	void add(boolean t, int u, int a, int id)
	{
		Ledger child = new Ledger(t, u, a, id);
		Ledger current = this;
		while(current.next!=null) current = current.next;
		current.next = child;
		
	}
	void buy(int unitp, int bought)
	{
		int already_bought=0;
		Ledger current = this;
		int boughtN = bought;
		while(current!=null)
		{
			if(current.txtype == false)
			{
				if(current.amount-current.sold_out >= boughtN)
				{
					System.out.println("Bought "+boughtN+" from transaction "+current.id+". Spent "+boughtN*unitp+" won.");
					current.sold_out = boughtN;
					return ;
				}
				else
				{
					if(current.amount > current.sold_out)
					{
					System.out.println("Bought "+(current.amount-current.sold_out)+" from transaction "+current.id+". Spent "+((current.amount-current.sold_out)* unitp )+" won.");

					boughtN -= current.amount - current.sold_out;
					current.sold_out = current.amount;
					}
				}
			}
			current = current.next;
		}
		System.out.println("Invalid transaction");
	}
	void get(int getId)
	{
		Ledger current = this;
		int temp = getId;
		while(temp>0)
		{current = current.next;temp-=1;}
		if(current.txtype == true) System.out.print("Buy, ");
		else System.out.print("Sell, ");
		System.out.println(current.unitprice+", "+current.amount);

	}
}
