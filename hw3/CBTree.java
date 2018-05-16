public class CBTree {
	public CBNode root;
	public int id=0;

	public CBTree(String st, String con)
	{
		/* st: 0 | 1 | st */
		root = new CBNode();
		newNode(st.charAt(0), con.charAt(0), root);
		constructTree(st, con);
	}
	/* Helper method. */
	public CBNode getRoot()	{return root;}
	public String postOrderTraversal()
	{	
		return postT(this.root);
	}
	public String inOrderTraversal()
	{
		return inOrderT(this.root);
	}
	public String postOrderStructure()
	{
		return postTS(this.root);
	}
	public String inOrderStructure()
	{
		return inOrderTS(this.root);
	}
	
	public void preOrder(CBNode n)
	{
		if(n==null)
			return ;
		System.out.print(n.label);
		preOrder(n.left);
		preOrder(n.right);
	}
	public void preOrderC(CBNode n)
	{
		if(n==null)
			return ;
		System.out.print(n.character);
		preOrderC(n.left);
		preOrderC(n.right);
	}
	private String inOrderT(CBNode n)
	{
		if (n!=null)
		{
			System.out.println("node id "+n.id);
			String a = inOrderT(n.left);
			String ret = ""+n.id;
			String b = inOrderT(n.right);
		/*	
		 *	if(a.equals("1"))	a = "";
			if(b.equals("1"))	b = "";
			if(ret.equals("1"))	ret = "";
		*/	return a+ret+b;
		}
		return "";
	}
	private String postT(CBNode n)
	{
		if (n==null)
			return "";
		String a = postT(n.left);
		String b = postT(n.right);
		String ret = ""+n.character;
		if(a.equals("1"))	a = "";
		if(b.equals("1"))	b = "";
		if(ret.equals("1"))	ret = "";
		return a+ret+b;
	}
	private String postTS(CBNode n)
	{
		if (n==null)
			return "";
		String a= postTS(n.left);
		String b= postTS(n.right);
		String ret = ""+n.label;
		return a+ret+b;
	}
	private String inOrderTS(CBNode n)
	{
		if (n==null)
			return "";
		String a = inOrderTS(n.left);
		String ret = ""+n.label;
		String b = inOrderTS(n.right);
		return a+ret+b;
	}
	private void newNode(char label, char character, CBNode n)
	{
		n.label = label;
		n.character = character;
		n.left = null;
		n.right = null;
		n.id = this.id;
		this.id++;
	}
	private void constructTree(String st, String con)
	{
		conHelper(st.substring(1, st.length()), con.substring(1, con.length()), this.root);
	}
	private String [] conHelper(String st, String con, CBNode n)
	{
		CBNode temp = new CBNode();
		String newcon=con;
		String newst=st;
		String argu[] = new String[2];

		n.printNode();

			if(n.id == 6)
				System.out.println(st);
		if(st.charAt(0) == '0')
		{
//			if(n.id == 6)
//				System.out.println(st);

			if(newst.charAt(0)=='0')
			{
				newNode(newst.charAt(0), newcon.charAt(0), temp);
				n.left = temp;

				newcon = newcon.substring(1, newcon.length());
				newst = newst.substring(1, newst.length());
				argu = conHelper(newst, newcon, n.left);
				newst = argu[0];
				newcon = argu[1];
			}
			else
			{
				newNode(newst.charAt(0), '1', temp);
				newst = newst.substring(1, newst.length());
				n.left = temp;
			}
			
			temp = new CBNode();
			if(newst.charAt(0)=='0')
			{
				newNode(newst.charAt(0), newcon.charAt(0), temp);
				newcon = newcon.substring(1, newcon.length());
				newst = newst.substring(1, newst.length());

				n.right = temp;
				conHelper(newst, newcon, n.right);
			}
			else
			{
				newNode(newst.charAt(0), '1', temp);
				newst = newst.substring(1, newst.length());
				n.right = temp;
			}
		}
		else
		{
			int count=0;
			if(n.left==null)
			{
				newNode(st.charAt(0), '1', temp);
				newst = newst.substring(1, newst.length());
				n.left = temp;
				count++;
			}
			if(n.right==null&&count==0)
			{
				CBNode temp2 = new CBNode();
				newNode(newst.charAt(0), '1', temp2);
				newst = newst.substring(1, newst.length());
				n.right = temp2;
			}
			if(count !=0)
			{
				conHelper(newst, newcon, n);
			}
		}

		argu[0] = newst;
		argu[1] = newcon;
		return argu;
	}

/*	private int recursion(CBNode node) {
		char next = string.getNext();
		int val = 1;
		if (next == 0) {
			node.character = characterlistStuff.next();
			node.label = 0;
			node.left = new CBNode();
			val += recurtion(node.left);

			node.right = new CBNode();
			val += recurtion(node.right);
		} else 	{
			node.label = 1;
		}
		return val;
	}
*/

	private void constructTree2(String st, String con)
	{
		int num_nodes = st.length();	// including root
		CBNode pool[] = new CBNode[num_nodes];
		pool[0] = this.root;
		int pixel=0;			// latest diverge pos
		int num_0=1;			// every 0 shares a char in con
		CBNode cur=root;
		
		for(int i=1; i<num_nodes; i++)
		{
			CBNode n1 = new CBNode();
			if(st.charAt(i)=='0')
			{
				newNode(st.charAt(i), con.charAt(num_0), n1);
				num_0++;
			}
			else	newNode(st.charAt(i), '1', n1);

			if( n1.label=='0')
			{
				if(cur.left==null)
				{
					cur.left =  n1;
					cur = n1;
					pool[i] = cur;
				}
				else
				{
					cur.right = n1;
					cur = n1;
					pool[i] = cur;

				}
			}else if(n1.label=='1')
			{
				if(cur.label=='0')
				{	
					pixel = i-1;		// at node pixel we branches
					cur.left =  n1;
					cur = n1;
					pool[i] = cur;
				}
				else	
					// new node label1 while cur node(the last one) also label 1
				{
					pool[pixel].right = n1;
					cur = n1;
					pool[i] = cur;
					pixel--;
				}

			}
			
		}
	}
}
