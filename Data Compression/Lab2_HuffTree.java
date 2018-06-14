import java.util.*;
public class Lab2_HuffTree{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		//Create a hash table that's going to store each char that occurs and it's frequency
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();

		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);

			//Count how many times you see a character
			if(table.get(current) == null)
				table.put(current, 1);
			else
				table.put(current, table.get(current)+1);
		}

		PriorityQueue<HuffmanTree> forest = new PriorityQueue<HuffmanTree>();
		//Populate the forest of trees
		Enumeration<Character> n = table.keys();
		while(n.hasMoreElements()){
			char temp = n.nextElement();

			HuffmanTree tree = new HuffmanTree();
			Node newNode = new Node(temp);

			//Set the node equal to the new letter
			tree.root = newNode;

			//The variable frequency stores the frequency of the letter
			tree.frequency = table.get(tree.root.letter);

			//The variable aFrequency stores the ASCII value of the letter
            		tree.aFrequency = (int)tree.root.letter;

			forest.add(tree);
		}

		while(forest.size() > 1){
			//Remove the two trees
			HuffmanTree temp1 = forest.poll();
			HuffmanTree temp2 = forest.poll();

			//Create a new tree which will be the combo tree
			HuffmanTree newTree = new HuffmanTree();

			newTree.root = new Node();

		    	//If the frequency is the same order the tree based on it's aFrequency
		    	if(temp1.frequency == temp2.frequency){
				if(temp1.aFrequency < temp2.aFrequency){
			    		newTree.root.left = temp1.root;
			    		newTree.root.right = temp2.root;
				}
				else{
					 newTree.root.left = temp2.root;
					 newTree.root.right = temp1.root;
				}
            		}
            		//Else just order the tree as normal
            		else{
                		newTree.root.left = temp1.root;
                		newTree.root.right = temp2.root;
            		}

			//frequency is just the sum of the frequency of each letter in the tree
			newTree.frequency = temp1.frequency + temp2.frequency;

			//aFrequncy is the ASCII value of letter with the lowest ASCII value in the tree
            		newTree.aFrequency = Math.min(temp1.aFrequency, temp2.aFrequency);

			forest.add(newTree);
		}

		HuffmanTree finalTree = forest.poll();

		//Prints the code for each char in one line
		for(int i = 0; i < input.length(); i++){
			String code = finalTree.getCode(input.charAt(i));

			System.out.print(code);
		}
	}
}

class HuffmanTree implements Comparable<HuffmanTree>{
	public Node root;
	public int frequency;
    	public int aFrequency;
	public Node left;
	public Node right;

	public HuffmanTree(){
		root = null;
	}

	//A compareTo is necessary for the priorty queue interface
	public int compareTo(HuffmanTree object){
		//This if, else if will sort the pQueue by frequency
		if(frequency - object.frequency > 0)
			return 1;
		else if(frequency - object.frequency < 0)
			return -1;

		//Will sort the pQueue by aFrequency if frequency of both trees is equal
		else{
            		if(aFrequency > object.aFrequency)
                		return 1;
            		else if(aFrequency < object.aFrequency)
                		return -1;
            		else
			    	return 0;
        	}
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

class Node{
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
