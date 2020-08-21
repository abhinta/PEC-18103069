import java.util.*; 

class Question4
{ 
	static boolean areStringsAnagrams(String a, String b)
	{ 
		//converts string to char arrays -> sorts it -> stores back as string
		char tempArray[] = a.toCharArray();
		Arrays.sort(tempArray); 
		a = new String(tempArray);

		//same procedure for b string
		tempArray = b.toCharArray();
		Arrays.sort(tempArray); 
		b = new String(tempArray);

		//since equals function is overloaded in String class, hence it is used
		return a.equals(b);
	} 
	public static void main(String[] args) 
	{ 
		String a,b;
		Scanner cin = new Scanner(System.in);
		a = cin.nextLine();
		b = cin.nextLine();
		boolean ans = areStringsAnagrams(a,b);
		if(ans==true)
			System.out.println("Strings are Anagrams"); 
		else
			System.out.println("Strings are not Anagrams"); 
	} 
} 
