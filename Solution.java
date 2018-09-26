package lab2;

import java.util.*;

import java.util.*;
public class Solution{
 public static void main(String[] args){
 Scanner scan = new Scanner(System.in);
 System.out.print("Enter sentence ");
 String input = scan.nextLine();
 String binaryString="";

 for(int i=0; i < input.length(); i++){

 int decimalValue = (int)input.charAt(i);

 String binary = Integer.toBinaryString(decimalValue);

 
 for(int j=7;j>binary.length();j--){
 binaryString+="0";
 }
 
 binaryString += binary+" ";

 }
 System.out.println(binaryString);
 

 int[] array = new int[256];
 for(int i=0; i < input.length(); i++){
 array[(int)input.charAt(i)]++;
 }
 
 for(int i=0; i<array.length; i++){
 if(array[i]>0){
 System.out.println("'"+(char)i+"' appeared "+array[i]+"times");
  }
 }

 }
}