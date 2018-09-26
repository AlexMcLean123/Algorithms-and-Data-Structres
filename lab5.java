package lab5;
import java.util.*;
public class lab5 {

static List<String> solutions = new ArrayList<String>();

static String[] dictionary;
    public static void main(String[] args){
        fileIO io = new fileIO();
        dictionary = io.load("X:\\cs211\\dictionary.txt");   
        
        for(int i =0; i<dictionary.length; i++){
        	dictionary[i]=dictionary[i].trim();
        }
        
        System.out.println("Enter letters");
        Scanner myscanner = new Scanner(System.in);
        String input = myscanner.nextLine();

        for(String dictionaryWord: dictionary){
        	if(canMake(dictionaryWord,input)){
        		solutions.add(dictionaryWord);
        	}
        }
        for(String word: solutions){
        	System.out.println(word);
        }
}
    public static boolean canMake(String word, String allowedLetters){
    	for(char c: word.toCharArray()){
    		if(allowedLetters.indexOf(""+c)==-1){
    			return false;
    		}
    		allowedLetters=allowedLetters.replaceFirst(""+c,"");
    	}
    	return true;
    }
}
