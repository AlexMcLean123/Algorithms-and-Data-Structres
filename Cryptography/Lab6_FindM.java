package lab6;

import java.util.Scanner;
public class Lab6_FindM {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		//Read in public key (p, g, result)
        	String A[] = sc.nextLine().split(" ");
		long p = Long.parseLong(A[0]);
		long g = Long.parseLong(A[1]);
		
		long result = Long.parseLong(A[2]);
		
		long x = 0;
		//Try every possible value for x between 0 and p until you get result
		for(long i = 0; i < p; i++){
			long temp = modPow(g, i, p);
			if(temp == result){
				x = i;
				break;
			}	
		}
		
		//Read in public cipher (c1, c2)
        	String B[] = sc.nextLine().split(" ");
		long c1 = Long.parseLong(B[0]);
		long c2 = Long.parseLong(B[1]);
		
		sc.close();
		
		//Follow forumula to extract M
		long power = p - 1 - x;
		long first = modPow(c1, power, p);
		
		//Print M
		System.out.println(modMult(first, c2, p));
	}
	
	//Methods provided by Phil
	public static long modPow(long number, long power, long modulus){
		 if(power==0)
			 return 1;
		 else if (power%2==0) {
			 long halfpower=modPow(number, power/2, modulus);
			 return modMult(halfpower,halfpower,modulus);
		 }else{
			 long halfpower=modPow(number, power/2, modulus);
			 long firstbit = modMult(halfpower,halfpower,modulus);
			 return modMult(firstbit,number,modulus);
		 }
	}
	 public static long modMult(long first, long second, long modulus){
		 if(second==0)
			 return 0;
		 else if (second%2==0) {
			 long half=modMult(first, second/2, modulus);
			 return (half+half)%modulus;
		 }else{
			 long half=modMult(first, second/2, modulus);
			 return (half+half+first)%modulus;
		 }
	}
}
