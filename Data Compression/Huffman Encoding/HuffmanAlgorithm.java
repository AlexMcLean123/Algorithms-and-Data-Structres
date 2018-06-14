import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Enumeration;
public class HuffmanAlgorithm{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		//Create a hash table that's going to store each char that occurs and it's frequency
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();

		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);
			String num = Integer.toBinaryString((int)current); //Convert char to ASCII binary string

			if(i % 4 == 0)
				System.out.println();

			//Pad the string
			if(num.length() < 7)
				for(int j = 0; j < 7 - num.length(); j++)
					num = "0" + num;

			System.out.print(num + " ");

			//Count how many times you see a character
			if(table.get(current) == null)
				table.put(current, 1);
			else
				table.put(current, table.get(current)+1);
		}
		System.out.println("\n");

		PriorityQueue<HuffmanTree> forest = new PriorityQueue<HuffmanTree>();

		Enumeration<Character> n = table.keys();
		while(n.hasMoreElements()){
			char temp = n.nextElement();
			//Prints how many times a character occurs
			System.out.println("'"+temp+"'" + " appeared " + table.get(temp) + " times");

			//Create forest of trees
			HuffmanTree tree = new HuffmanTree();
			Node newNode = new Node(temp);
			//Set the node equal to the new letter
			tree.root = newNode;
			//Set the frequency to how many times it occured
			tree.frequency = table.get(tree.root.letter);

			forest.add(tree);
		}
		System.out.println();

		while(forest.size() > 1){
			//Remove the two trees
			HuffmanTree temp1 = forest.poll();
			HuffmanTree temp2 = forest.poll();

			//Create a new tree which will be the combo tree
			HuffmanTree newTree = new HuffmanTree();

			newTree.root = new Node();
			newTree.root.left = temp1.root;
			newTree.root.right = temp2.root;

			newTree.frequency = temp1.frequency + temp2.frequency;
			forest.add(newTree);
		}

		HuffmanTree finalTree = forest.poll();

		double originalBits = input.length() * 7;
		double newBits = 0;

		//Prints the code for each char and calculates how many bits there are now
		for(int i = 0; i < input.length(); i++){
			String code = finalTree.getCode(input.charAt(i));
			newBits += code.length();

			System.out.print(code + " ");
		}
		System.out.println("\n");

		int result = (int)((newBits / originalBits) * 100);
		System.out.println("Compressed size is " + (int)newBits + " bits / " + (int)originalBits + " bits = " + result + "%");
	}
}