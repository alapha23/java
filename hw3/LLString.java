public class LLString
{
	/* Add internal field variables here. */
	Letter mystr [];
	int length;
	
	/* Constructor. */
	public LLString(String str)
	{
		this.length = str.length();
		mystr = new Letter[this.length];
		mystr[0] = new Letter(str.charAt(0));
		for(int i=1; i<this.length; i++)
		{
			mystr[i] = new Letter(str.charAt(i));
			mystr[i-1].next = mystr[i].next;
		}
	}

	/* Add methods here. */
	/* For example. */
	public char charAt(int index){	return this.mystr[index].c;}
	public int length(){return this.length;}
	public String toString()
	{
		String ret="";
		for(int i=0; i<this.length; i++)
			ret += ""+mystr[i].c;
		return ret;
	}

	public int compareTo(LLString another)
	{
		int ret=0;
		int k=0;
		if(this.length != another.length)
			return this.length - another.length;
		while(another.mystr[k].c == this.mystr[k].c)
		{
			k++;
			if (k==this.length) break;
		}
		ret = this.mystr[k].c - another.mystr[k].c;
		return ret;
	}

	public int compareToIgnoreCase(LLString llstr)
	{
		Letter	
	}
}

class Letter
{
	char c;
	Letter next;
	public Letter(char c)
	{this.c = c;next=null;}
	public Letter(char c, Letter n)
	{this.c = c;this.next=n;}
}
