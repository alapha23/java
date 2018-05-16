public class CBTreeTest
{
	public static void main(String[] ar)
	{
//		CBTree cbt = new CBTree("0101011", "TCS");
		CBTree cbt = new CBTree("00011101011", "ABCDE");
		CBNode root = cbt.root;

		System.out.print("root: ");
		root.printNode();
		System.out.print("left of root: ");
		root.left.printNode();
		System.out.print("left of 1: ");
		root.left.left.printNode();
		System.out.print("right of 1: ");
		root.left.right.printNode();
		System.out.print("left of 2: ");
		root.left.left.left.printNode();
		System.out.print("right of 2: ");
		root.left.left.right.printNode();

		System.out.print("right of root: ");
		root.right.printNode();
		System.out.print("left of 6: ");
		root.right.left.printNode();
		System.out.print("right of 6: ");
		root.right.right.printNode();
		System.out.print("left of 8: ");
		root.right.left.left.printNode();
		System.out.print("right of 8: ");
		root.right.right.right.printNode();


	

		System.out.println(cbt.postOrderTraversal());	// good to go
		System.out.println(cbt.inOrderTraversal());
		System.out.println(cbt.postOrderStructure());
		System.out.println(cbt.inOrderStructure());

//		cbt.preOrder(cbt.root);
//		System.out.println();
		cbt.preOrderC(cbt.root);

	}
}

