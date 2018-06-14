import java.util.Scanner;
public class quicksort {
public static String[] theArray;
public static void main(String[]args){
    Scanner scan = new Scanner(System.in);
    int num = Integer.parseInt(scan.nextLine());
    theArray = new String[num];
    for(int i=0;i<num;i++){
        theArray[i]=scan.nextLine();
    }

    recQuickSort(theArray,0,theArray.length-1);

    for(int i=0;i<num;i++){
        System.out.println(theArray[i]);
    }
}


public static void recQuickSort(String array[], int left, int right) {

	 if(right-left <= 0)
	 return;

	 else{
	 String pivot = theArray[right];
	 int partition = partitionIt(left, right, pivot);
	 recQuickSort(array,left, partition-1);
	 recQuickSort(array,partition+1, right);
  }
}

public static int partitionIt(int left, int right, String pivot){

	 int leftPtr = left-1;
	 int rightPtr = right;
	 while(true)
	 {
	 while(compareTo(theArray[++leftPtr], pivot )){}
	 while(compareTo(theArray[--rightPtr],pivot)){}


	 if(leftPtr >= rightPtr)
	 break;
	 else
	 swap(theArray,leftPtr, rightPtr);
	 }
	 swap(theArray,leftPtr, right);
	 return leftPtr;
	}

public static void swap(String arr[], int one, int two){
	String temp = arr[one];
	arr[one] = arr[two];
	arr[two] = temp;
}

public static boolean compareTo(String a, String b){
	char charA = getGreatest(a);
	char charB = getGreatest(b);
	if(charA>charB)
	{
		return true;
	}

	else if(charA<charB)
	{
		return false;
	}

	if(a.compareTo(b)>0)
	{
		return true;
	}
return false;
}


public static char getGreatest(String x){
	if(x.length()==0){
		return 'x';
	}
	char biggest = x.charAt(0);
	for(int i =1; i<x.length(); i++){
		char current = x.charAt(i);
		if(current>biggest){
			biggest =current;
		}
	}
	return biggest;
}
}