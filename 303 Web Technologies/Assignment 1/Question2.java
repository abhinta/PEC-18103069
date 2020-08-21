import java.util.*;
class Question2 {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter Paragraph: ");
		String paragraph = cin.nextLine();

		// String [] words = paragraph.split(" ");
		// String ans = "";//we'll keeping appending words into it

		System.out.println("Enter size of vector: ");
		int n = cin.nextInt();

		String [] replace = new String[n];
		for(int i=0; i<n ; i++)
		{
			replace[i]=cin.next();
		}
		cin.close();

		//solution starts from here
		
		String ansParagraph = paragraph;
		int index = ansParagraph.indexOf(" ");
		String firstWord = ansParagraph.substring(0,index);
		//solving for first word
		for(int i=0; i<n ; i++)
		{
			if(firstWord.equals(replace[i])){
				String temp = String.valueOf(firstWord.charAt(0));//"H"
				for(int j=1;j<firstWord.length();j++)
					temp += "*";
				ansParagraph = temp + ansParagraph.substring(firstWord.length());
			}
		}
		//solving for (internal) words ahead of first word
		for(int i=0; i<n ; i++)
		{
			String temp = " " + replace[i] + " ";// temp becomes " Happy " 
			int found = ansParagraph.indexOf(temp);//temp is searched

			if(found != -1){
				//replacement occurs
				String replacement = String.valueOf(replace[i].charAt(0));//"H"
				for(int j=1;j<replace[i].length();j++)
					replacement += "*";
				//replacement becomes "H****"

				ansParagraph = ansParagraph.replaceAll(temp, " " + replacement + " ");//replaces " Happy " with " H**** "
			}
		}
		
		//solving for words ending in '.'
		for(int i=0; i<n ; i++)
		{
			String temp = " " + replace[i] + ".";// temp becomes " Happy " 
			int found = ansParagraph.indexOf(temp);//temp is searched

			if(found != -1){
				//replacement occurs
				String replacement = String.valueOf(replace[i].charAt(0));//"H"
				for(int j=1;j<replace[i].length();j++)
					replacement += "*";
				//replacement becomes "H****"
				
				ansParagraph = ansParagraph.replaceAll(temp, " " + replacement + ".");//replaces " Happy " with " H**** "
			}
		}

		System.out.println("Output:"+ansParagraph);
	}

}
