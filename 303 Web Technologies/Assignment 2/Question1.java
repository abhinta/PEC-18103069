import java.util.*;
class Question1 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter 1st String: ");
		String string1 = cin.nextLine();
		System.out.println("Enter 2nd String: ");
		String string2 = cin.nextLine();

		int i=0;
		boolean areEqual = true;//string1 = string2
		while(i!=string1.length() && i!=string2.length()){
			char ch1 = string1.charAt(i);
			char ch2 = string2.charAt(i);

			if(ch1 != ch2)
			{
				areEqual = false;
				break;
			}
			i++;
		}

		//if string 1 is a substring of string2 or vice-versa
		int minLength = string1.length() < string2.length() ? string1.length() : string2.length();
		if(areEqual == true){
			//eg. "abcdefxyz" and "abcdef"

			//areEqual = true for sure
			if(string1.length() == string2.length())
				System.out.print("String 1 and String 2 are equal lexicographically");
			else{
				if(string1.length() > string2.length())
				System.out.print("String 1 is greater than String 2 lexicographically");
				else
				System.out.print("String 2 is greater than String 1 lexicographically");
			}

		}
		else{
			
			char ch1 = string1.charAt(i);
			char ch2 = string2.charAt(i);
			if(ch1 > ch2)
				System.out.print("String 1 is greater than String 2 lexicographically");
			else
				System.out.print("String 2 is greater than String 1 lexicographically");
		}
	}

}
