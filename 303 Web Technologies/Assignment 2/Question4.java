import java.util.*;
class Question4 {

	public static void main(String[] args) {
		/*
		Mathematically, 
		sigma(i) = i*(i+1)/2
		comparing with i^2...keeping in mind i>0
		
		i*(i+1)
		-------	=	i^2
			2

		since, i>0, hence, divind both sides by i
		
		i+1
		---	=	i 		[ a linear equation thus, one solution unless it turns a universal truth]
		 2
		multiplying both sides by 2 and subtracting i from both sides

		Clearly, i = 1 is the only solution for this problem

		*/
		long i = Integer.MAX_VALUE;//widening happens (small data type's value can be stored in larger (valid) data type)
		//long is preferred since Integer.MAX_VALUE * Integer.MAX_VALUE would be maximum 2^61 which long can easily handle
	    while(i>0)
	    {
	        long sum = (i * (i + 1))/2;
	        if(sum == i * i){
	    		System.out.println(i);
	        	// break;
	        }
	        i--;
	    }
	}

}
