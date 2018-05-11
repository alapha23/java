import java.util.*;

class lab10
{
	public static void main(String args[])
	{
		BinaryTree t = new BinaryTree();
		t.insert('A');
		t.insert('B');
		t.insert('C');
		t.preorder();
	}
}

class BinaryTree
{
	private BinaryTreeNode root;

	public BinaryTree()
	{
		this.root=null;
	}

	public void insert(char label)	
	{
		if(this.root == null)
		{
			this.root = new BinaryTreeNode(label);
			return ;
		}
		BinaryTreeNode newnode = new BinaryTreeNode(label);
		BinaryTreeNode temp = this.root;
		while(temp != null)
		{
			if(label < temp.label)
			{
				if(temp.left == null)
				{
					temp.left = newnode;
					return ;
				}
				else{temp = temp.left;}
			}
			else
			{	
				if(temp.left == null)
				{
					temp.left = newnode;
					return ;
				}
				else{temp = temp.left;}
			}
		}
	}
	public void preorder()
	{
		preorder(this.root);
		System.out.println();
	}
	private void preorder(BinaryTreeNode n)
	{
		if(n == null)
			return ;
		System.out.print(n.label);
		preorder(n.left);
		preorder(n.right);
	}
	

}

class BinaryTreeNode
{
	char label;
	BinaryTreeNode	left;
	BinaryTreeNode	right;

	public BinaryTreeNode(char label)
	{
		this.label = label;
		this.left = null;
		this.right = null;
	}

}
