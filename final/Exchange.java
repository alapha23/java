import java.util.Random;
import java.util.Scanner;

public class Exchange
{
	/* Random generator used in the class.*/
	public static Random rnd;

	/* Scanner used in the class. */
	public static Scanner sc;
	public static int unitp;
	public static int userHav = 0;
	public static int id=0;
	public static int left=100;
	public static int doRandom()
	{
		double prob = rnd.nextDouble();
		if(prob >= 0.75)
		{
			/* Increase. */
			return 1;
		}
		else if(prob <= 0.25)
		{
			/* Decrease. */
			return -1;
		}
		else
		{
			System.out.println("No change.");
			return 0;
		}
	}
	public static int str2int(String s)
	{
		int ret=0;
		int len = s.length();
		if(len != 1)
		{	
		for(int i=0; i<len; i++)
		{
			
			int add=10;
			if(i == len-1)	add=1;
			if(s.charAt(i)=='\n'||s.charAt(i)==' ')	break;
			for(int j=i; j<len-2; j++)add = add *10;
			ret += 	add * (s.charAt(i)-'0');
		}
		}else 
		ret = s.charAt(0) - '0';
		return ret;
	}

	public static void main(String[] ar)
	{
		/* Initialize. */
		rnd = new Random();
		sc = new Scanner(System.in);
		unitp=rnd.nextInt(9901) + 100;
		if(unitp>10000)unitp = 10000;
		Ledger ledger = new Ledger(false, unitp, 100, 0);
		int asset = 1000000;
		while(true)
		{
			System.out.print("Type command: ");
			String s = sc.nextLine();

			if(s.equalsIgnoreCase("e"))
			{
				break;
			}
			switch(s.charAt(0))
			{
				case 'p':
					{
					System.out.println("[Won] User having: "+asset);
					System.out.println("[Currency] Unit price: "+unitp+" Remaining: "+left+" User having:"+userHav);
					break;
					}
				case 'b':
					{
					String amount = s.substring(2, s.length());
					int bought = str2int(amount);
					if(left < bought)
					{System.out.println("Invalid transaction");break;}
					if(asset < bought * unitp+100)
					{System.out.println("No money to buy");break;}
					asset -= bought * unitp;	// how much money we have left
					userHav += bought;		
					left -= bought;
					ledger.buy(unitp, bought);
				//	System.out.println("Bought "+bought+" from transaction "+id+". Spent "+bought*unitp+" won.");
					id +=1;
					ledger.add(true, unitp, bought, id);
					break;
					}
				case 's':
					{
					String amountS = s.substring(2, s.length());
					int sold = str2int(amountS);
					if(userHav < sold)
					{System.out.println("Invalid transaction");break;}
					asset += sold * unitp;	// how much money we have left
					userHav -= sold;		
					left += sold;
					System.out.println("Selling "+sold+". Got "+sold*unitp+" won.");
					id +=1;
					ledger.add(false, unitp, sold, id);
					break;}
				case 'g':
					{
						String str = s.substring(2, s.length());
						int getId = str2int(str);
						ledger.get(getId);
						break;
					}
					
				default:
			}
			
			// Change unit price
			int change = doRandom();
			if(change>0)	
			{
				unitp = unitp - unitp/10 + rnd.nextInt()%(unitp/5);
				if(unitp>10000)unitp = 10000;

				System.out.println("Unit price increased to "+unitp);
			}
			else if(change<0){
				unitp = unitp - unitp/10 + rnd.nextInt()%(unitp/5);
				if(unitp<100)unitp = 100;

				System.out.println("Unit price decreased to "+unitp);

			}
		}
		sc.close();
	}


}
