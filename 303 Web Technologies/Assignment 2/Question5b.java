import java.util.*;
// 7 milliseconds

class Question5b {

    public static void main(String[] args) {
        
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        Set<Integer> universal = new HashSet<Integer>();
        
        universal.addAll(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        Scanner cin = new Scanner(System.in);
        
        System.out.println("No. of elements in first set:");
        int n1 = cin.nextInt();
        System.out.println("No. of elements in second set:");
        int n2 = cin.nextInt();
        
        //set 1
        System.out.println("Elements of set 1:");
        for(int i = 0; i < n1; i++) {
            int x = cin.nextInt();
            while(x < 0 || x > 10) {
                System.out.println("Enter a number between 0 and 10");
                x = cin.nextInt();
            }
            set1.add(x);
        }
        
        //set 2
        System.out.println("Elements of set 2:");
        for(int i=0; i< n2 ; i++) {
            int x=cin.nextInt();
            while(x<0 || x>10) {
                System.out.println("Enter a number between 0 and 10");
                x=cin.nextInt();
            }
            set2.add(x);
        }
        
        long start = System.currentTimeMillis();

        Set<Integer> union_ans = new HashSet<Integer>(set1);
        union_ans.addAll(set2);
        System.out.println("Union: " + union_ans);
        
        Set<Integer> int_ans = new HashSet<Integer>(set1);
        int_ans.retainAll(set2);
        System.out.println("Intersection: " + int_ans);
        
        Set<Integer> compset1 = new HashSet<Integer>(universal);
        compset1.removeAll(set1);
        System.out.println("Complement of set 1: " + compset1);
        
        Set<Integer> compset2 = new HashSet<Integer>(universal);
        compset2.removeAll(set2);
        System.out.println("Complement of set 2: " + compset2);
        
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" milliseconds");
    }
}