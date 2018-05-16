public class CBNode
{
	public char label;	/* 0 or 1 */
	public char character;	/* a single upper-case letter */
	public CBNode left;
	public CBNode right;
	

	public int id;

	public void printNode()
	{
		System.out.println(id+" : "+label+" "+character);
	}
}
