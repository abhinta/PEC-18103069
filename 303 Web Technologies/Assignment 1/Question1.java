import java.util.*;
class Question1 {
	static int ansCount;
	static int countOccurences(String sub, String str){
		int index = 0;
		int count = 0;
		while(index!=-1){

			index = str.indexOf(sub,index);
			if(index!=-1)
			{
				count++;
				index++;
			}

		}
		return count;
	}
	static String swap(String s, int i, int j){
		char ch[] = s.toCharArray(); 
        char temp = ch[i]; 
        ch[i] = ch[j]; 
        ch[j] = temp;
        return new String(ch);
	}
	static void permutations(String sub, int fixed, String str){
		if(fixed==sub.length()){
			ansCount += countOccurences(sub,str);
		// System.out.println(sub);
			return;
		}
		for(int i=fixed;i<sub.length();i++){

			sub = swap(sub,i,fixed);
			permutations(sub,fixed+1,str);
			sub = swap(sub,i,fixed);
		}
	}

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter Main String: ");
		String str = cin.nextLine();

		System.out.println("Enter subString: ");
		String sub = cin.nextLine();
		cin.close();

		permutations(sub,0,str);
		// System.out.println(ansCount);
		
		System.out.println("No. of times substring occurs in main String\n(considering permutations of substring as well):" + ansCount);
	}

}
