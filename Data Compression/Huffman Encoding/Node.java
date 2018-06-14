public class Node{
	public char letter;
	public Node left;
	public Node right;
	public Node parent;

	public Node(){
		letter = '@';
		left = null;
		right = null;
		parent = null;
	}
	public Node(char value){
		letter = value;
		left = null;
		right = null;
		parent = null;
	}
}