/*
 * Sort a list of strings alphabetically but only based on the letter in each string that's furthest along in the alphabet
 * eg. ["za", "yb"] will sort to ["yb", "za"]
 * If the letters furthest along in the alphabet in both strings are the same letter then sort alphabetically
 * eg. ["zb", "za"] will sort to ["za", "zb"]
 */
import java.util.*;
public class Lab3_QuickSort{

    public static String[] myarray;

    public static void main(String[] args){

        Scanner myscanner = new Scanner(System.in);
        int num = Integer.parseInt(myscanner.nextLine());
        String[] array = new String[num];
        myarray = new String[num];
        for(int i=0;i<num;i++){
            myarray[i]=myscanner.nextLine();
        }

        recQuickSort(myarray);

        for(int i=0;i<num;i++){
            System.out.println(myarray[i]);
        }
    }

    public static void recQuickSort(String arr[]){
        recQuickSort(arr, 0, arr.length-1);
    }

     //This will execute the quick sort
     public static void recQuickSort(String arr[], int left, int right){
	//base case
	if(left >= right)
		return;

	//Pivot is rightmost element in the array
	int pivot = right;
	int rightScan = right-1;
	int leftScan = left;

	while(leftScan < pivot && rightScan >= 0){
		if(leftScan == rightScan){
			if(compare(arr[leftScan], arr[pivot]))
				swap(arr, leftScan, pivot);
			break;
		}

		if(leftScan > rightScan){
			swap(arr, leftScan, pivot);
			break;
		}

		if(compare(arr[leftScan], arr[pivot]) && !compare(arr[rightScan], arr[pivot])){
			swap(arr, leftScan, rightScan);
			leftScan++;
			rightScan--;
		}

		else if(compare(arr[leftScan], arr[pivot]))
			rightScan--;

		else if(!compare(arr[rightScan], arr[pivot]))
			leftScan++;

		else{
			leftScan++;
			rightScan--;
		}
	}
	recQuickSort(arr, left, rightScan);
	recQuickSort(arr, leftScan+1, right);
    }

    //This method will find out which string should come first in the sorted array
    public static boolean compare(String one, String two){
        char temp1[] = one.toCharArray();
        char temp2[] = two.toCharArray();

	//Sorting both strings will mean the letter furthest along
	//in the alphabet will be the last entry in the char array
        Arrays.sort(temp1);
        Arrays.sort(temp2);

	//Only compares the two strings letters which are latest in the alphabet
	//If string one has a letter later in the alphabet then it comes earlier in sorted array
        if(temp1[temp1.length-1] > temp2[temp2.length-1])
            return true;

	//If string one has a letter earlier in the alphabet then it comes later in sorted array
        else if(temp1[temp1.length-1] < temp2[temp2.length-1])
            return false;

	//If both strings have same latest letter then sort alphabetically
        else{
            if(one.compareTo(two) > 0)
                return true;
            else
                return false;
        }
    }

    public static void swap(String arr[], int one, int two){
		String temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
}
