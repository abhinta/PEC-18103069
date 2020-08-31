import java.util.*;
class Question3 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter no. of Strings: ");
		int n = cin.nextInt();
		//fflush problem might exist, eg. storing enter keystroke in buffer
		cin.nextLine();
		//issues resolved
		String[] strArray = new String[n];
		for(int i=1;i<=n;i++){
			System.out.println("Enter String "+i+": ");
			strArray[i-1] = cin.nextLine();	
		}
		
		//sorting
		for(int i=0;i<n;i++){
			for(int j=0;j<n-1-i;j++){
				if(strArray[j].compareTo(strArray[j+1]) > 0){
					//swapping
					String temp = strArray[j];
					strArray[j] = strArray[j+1];
					strArray[j+1] = temp;
				}
			}		
		}
		System.out.println("After sorting strings:");
		for(int i=0;i<n;i++){
			System.out.println(strArray[i]);
		}


	}

}
