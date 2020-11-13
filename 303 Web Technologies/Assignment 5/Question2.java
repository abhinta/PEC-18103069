import java.util.*;

class Question2 {

   private final static int MAX = 100000;
   private volatile static int maxDivisorCount = 0;
   private volatile static int maxDivisorInt;

   synchronized private static void alterResult(int divs, int divInt) {

      if (divs > maxDivisorCount) {
         maxDivisorCount = divs;
         maxDivisorInt = divInt;
      }

   }
   private static class DivisorThread extends Thread {

      int min, max;

      public DivisorThread(int min, int max) {
         this.min = min;
         this.max = max;
      }

      public void run() {

         int maxDiv = 0;
         int maxDivInt = 0;
         for (int i = min; i < max; i++) {

            int divisors = countDivisors(i);
            if (divisors > maxDiv) {
               maxDiv = divisors;
               maxDivInt = i;
            }
         }
         alterResult(maxDiv,maxDivInt);
      }
   }
   
   private static void countMaxDivisors(int threadCount){
      System.out.println();
      System.out.println("Initiating process using " + threadCount + " threads\n");

      long startTime = System.currentTimeMillis();

      DivisorThread[] worker = new DivisorThread[threadCount];

      int integersPerThread = MAX / threadCount; 
      
      int start = 1;  
      int end = start + integersPerThread - 1;

      for (int i = 0; i < threadCount; i++) {
         if (i == threadCount - 1) {
            end = MAX;  
         }
         worker[i] = new DivisorThread(start, end);
         start = end + 1;    
         end = start + integersPerThread - 1;
      }

      //initiating threads
      for (int i = 0; i < threadCount; i++)
         worker[i].start();

      //waiting till all threads die
      for (int i = 0; i < threadCount; i++) {
         while (worker[i].isAlive()) {
            try {
               worker[i].join();
            }
            catch (InterruptedException e) {
            }
         }
      }

      long elapsedTime = System.currentTimeMillis() - startTime;
      System.out.println("Integer with max divisors in range [1," + MAX + "]: " + maxDivisorInt);
      System.out.println("No. of divisors of " + maxDivisorInt + ": " + maxDivisorCount);
      System.out.println("Time elapsed:  " + (elapsedTime/1000.0) + " seconds");
   }
   // public static int countDivisors(int n) {
   //    int count = 0;
      
   //    for (int i = 1; i <= Math.sqrt(n); i++) {
   //       if (n % i == 0)
   //       {
   //          if(n / i == i)
   //             count++;
   //          else
   //             count += 2;
   //       }
   //    }
   //    return count;
   // }
   public static int countDivisors(int n) {
      int count = 0;
      for (int i = 1; i <= n; i++) {
         if (n % i == 0)
            count++;
      }
      return count;
   }
 
   public static void main(String[] args) {

      Scanner cin = new Scanner(System.in);
      System.out.print("Input thread count (1 to 10): ");
      int threadCount = cin.nextInt();

      while (threadCount < 1 || threadCount > 10) {
         System.out.print("Input thread count (1 to 10): ");
         threadCount = cin.nextInt();
      }

      countMaxDivisors(threadCount);

   }
}