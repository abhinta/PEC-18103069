import java.util.*;
class Question2 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter no. of elements: ");
		int n = cin.nextInt();
		
		int[] arr = new int[n];
		for(int i=0;i<n;i++){
			System.out.println("Enter element "+ (i+1) +": ");
			arr[i] = cin.nextInt();	
		}
		
		//sorting

		
		int[] indexArray = new int [21];//default all values are initialized to 0
		for(int i = 0; i < n ; i++){
			indexArray[arr[i]]++;
		}

		//clubbing up index count
		for(int i = 1; i < 20 ; i++){
			indexArray[i] = indexArray[i] + indexArray[i-1];
		}

		//placing elements in copyArray, i.e. copyArray is just sorted version of arr
		int[] copyArray = new int[n];
		for(int i = 0; i < n ; i++){
			int position = indexArray[arr[i]];
			copyArray[position-1] = arr[i];
			indexArray[arr[i]]--;
		}	

		arr = copyArray;//previous memory of arr is taken away by garbage collector
		System.out.println("After Sorting: ");
		for(int i=0;i<n;i++){
			System.out.print(arr[i] + " ");
		}
			
	}

}
