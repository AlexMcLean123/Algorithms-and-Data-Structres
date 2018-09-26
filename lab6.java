package lab6;
import java.util.Arrays;
import javax.sound.sampled.*;
import java.util.Scanner;
public class lab6 {
	static String[] alpha = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
	        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
	        "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8",
	        "9", "0","!", ",", "?", ".", "'"};
	static String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
	        "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
	        "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
	        "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
	        "-....", "--...", "---..", "----.", "-----","-.-.--", "--..--", "..--..", ".-.-.-", ".----.",};


	public static String decodeEnglish(String englishCode) {
	    String build = "";
	    String change = englishCode.trim();
	    String[] words = change.split(" ");
	    for(String word : words){
	        for(int i=0;i<word.length();i++){
	            for(int x=0; x<morse.length;x++){
	                if(word.substring(i, i+1).equalsIgnoreCase(alpha[x])){
	                    build=build+morse[x]+" ";
	                }
	            }
	        }
	        build+="  ";
	    }

	    return build;
	}

	public static void main(String[] args) throws LineUnavailableException{
		
		Scanner scan = new Scanner(System.in);
		String input1=scan.nextLine();
		System.out.println(decodeEnglish(input1));
	    for(int i =0; i<decodeEnglish(input1).length(); i++){
	    	if(decodeEnglish(input1).charAt(i)=='.'){
	    		tone(700,500,10.0);
	    	}
	    	else if(decodeEnglish(input1).charAt(i)=='-'){
	    		tone(1200, 1000, 10.0);
	    	}
	    	else{
	    		 tone(700,1500,0.0);
	    	}
	    }
	    
}
	
	
	public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{
		float SAMPLE_RATE = 8000f;
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i=0; i < msecs*8; i++) {
		double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
		buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
		sdl.write(buf,0,1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
		}
}
