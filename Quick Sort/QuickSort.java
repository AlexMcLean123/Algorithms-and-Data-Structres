public class QuickSort{

	public static void recQuickSort(int arr[]){
		recQuickSort(arr, 0, arr.length-1);
	}

	public static void recQuickSort(int arr[], int left, int right){
		//base case, if it's a single value
		if(left >= right)
			return;

		//Chosing pivot to be the rightmost element of the array segment
		int pivot = right;
		int rightScan = right-1;
		int leftScan = left;


		while(leftScan < pivot && rightScan >= 0){
			if(leftScan == rightScan){
				if(arr[leftScan] > arr[pivot])
					swap(arr, leftScan, pivot);
				break;
			}

			if(leftScan > rightScan){
				swap(arr, leftScan, pivot);
				break;
			}

			//If you've found elements smaller and greater than the pivot then swap them
			if(arr[leftScan] > arr[pivot] && arr[rightScan] < arr[pivot]){
				swap(arr, leftScan, rightScan);
				leftScan++;
				rightScan--;
			}

			//If the right scan is greater than pivot
			else if(arr[leftScan] > arr[pivot])
				rightScan--;

			//If the left scan is less than pivot
			else if(arr[rightScan] < arr[pivot])
				leftScan++;

			//If neither of them is correct
			else{
				leftScan++;
				rightScan--;
			}
		}

		//Sort everything to the left of the pivot
		recQuickSort(arr, left, rightScan);
		//Sort everything to the right of the pivot
		recQuickSort(arr, leftScan+1, right);
	}

	public static void swap(int arr[], int one, int two){
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}

	//Example from lab
	public static void main(String args[]){
		int arr[] = {7, 8, 2, 5, 1, 9, 3, 6};
		recQuickSort(arr);
		for(int i : arr)
			System.out.print(i + " ");
		//1 2 3 5 6 7 8 9
	}
}