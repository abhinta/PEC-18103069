import java.util.*;
class Question6 {

	public static void main(String[] args) {
	    // Scanner cin = new Scanner(System.in);
	    if(args.length == 0)
	    System.out.println("Please enter a number at console");
		else{
	    	long n = Integer.parseInt(args[0]);
	    	System.out.println("Number Entered is: " + n);
	    	//long is used to handle cases when n is very large, eg Integer.MAX_VALUE
	    	if(n<=0)
	    	System.out.println("Enter a valid number > 0");
			else{
	    		System.out.println("Hailstone sequence for " + n + " is: ");
	    		
	    		while(n != 1)
			   	{
			   		System.out.print(n + " ");
    		    	if(n % 2 == 1)
    		    		n = 3*n + 1;
    		    	else
    		    		n = n / 2;
	    		}
	    		System.out.print(1);
			}



		}	
	}

}
