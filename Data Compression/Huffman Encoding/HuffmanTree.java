public class HuffmanTree implements Comparable<HuffmanTree>{
	public Node root;
	public int frequency;
	public Node left;
	public Node right;

	public HuffmanTree(){
		root = null;
	}
	public int compareTo(HuffmanTree object){
		if(frequency - object.frequency > 0)
			return 1;
		else if(frequency - object.frequency < 0)
			return -1;
		else
			return 0;
	}

	String path = "error";
	public String getCode(char x){
		inOrder(root, x, "");
		return path;
	}

	//Prints leftChild - root - rightChild
	public void inOrder(Node localRoot, char letter, String path){
		if(localRoot != null){
			if(localRoot.letter == letter)
				this.path = path;
			else{
				inOrder(localRoot.left, letter, path+"0");
				inOrder(localRoot.right, letter, path+"1");
			}
		}
		return;
	}
}