/* The letter frequency string I used to determine it was Spanish was
 * "e a o s r n i d l c t u m p b g y í v q ó h f z j é á ñ x ú ü w k"
 * The letter frequency string that translates it very well in to Caste on the Hill is
 * "e a o s r n c d l i m t u p y h b v y g ó j Y q j f E ñ x T ü C k"
 */
import java.util.*;
import java.io.*;

public class Lab5_EncryptedText{
	public static void main(String args[]) throws FileNotFoundException{
		Scanner file = new Scanner(new FileReader(/*File location*/));
		String input = "";

		//Read in the encryted text as a string
		while(file.hasNext())
			input += file.nextLine();

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

		//This will create a list of all the chars in the text in descending order of frequency
		ArrayList<Character> result = sortValue(table);

		//language maps characters from encrypted text to characters from language profile
		Hashtable<Character, Character> language = createLanguage(result);

		input = input.replaceAll("r", " ");

		System.out.println(input);

		char[] charInput = input.toCharArray();

		//Iterate through encrypted text and replace each character to what it maps to in hastable
		for(int i = 0; i < input.length(); i++){
			//Temp is current letter in input(encoded.txt)
			char current = charInput[i];

			if(language.get(current) != null){
				//'m' and '#' are punctuations
				if(current == 'm' || current == '#')
					charInput[i] = '.';

				else
					charInput[i] = language.get(current);
			}
		}
		String answer = new String(charInput);

		System.out.println(answer);
	}
	public static ArrayList<Character> sortValue(Hashtable<Character, Integer> t){

       //Transfer as List and sort it
       ArrayList<Character> l = new ArrayList<Character>(t.keySet());
       Collections.sort(l, new Comparator<Character>(){

         public int compare(Character o1, Character o2) {
            if(t.get(o1) < t.get(o2))
            	return 1;
            else if(t.get(o1) > t.get(o2))
            	return -1;
            else
            	return 0;
         }});
       	 return l;
    }
	public static Hashtable<Character, Character> createLanguage(ArrayList<Character> list){
		Scanner sc = new Scanner(System.in);
		//Take s string which is the alphabet of a language in descending order of frequency
		String temp = sc.nextLine().replaceAll(" ", "");
		sc.close();

		char[] letters = temp.toCharArray();
		list.remove(0); //Removes space character (the most frequent char)

		Hashtable<Character, Character> language = new Hashtable<Character, Character>();

		for(int i = 0; i < temp.length(); i ++)
			language.put(list.get(i), letters[i]);

		return language;
	}
}