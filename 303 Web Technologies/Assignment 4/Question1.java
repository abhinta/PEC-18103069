import java.util.Arrays;
import java.util.Scanner;

class Question1{
    public static void main(String[] args)
    {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        in.nextLine();
        String[] crops = new String[n];
        for (int i = 0;i < n; i++){
            crops[i] = in.nextLine().trim();
        }
        System.out.print(replant(crops));
    }

    public static int replant(String[] crops){
        int len = crops.length;
        String[] duplicateCrop = new String[len];
        int i = 0;
        while (i < len) {
            StringBuilder copy= new StringBuilder(crops[i]);
            duplicateCrop[i] =  copy.reverse().toString();
            i++;
        }
        int choice1 = solution(crops);
        int choice2 = solution(duplicateCrop);
        return Math.min(choice1, choice2);
    }

    public static int solution(String[] crops) {
        int x = crops.length;
        int y = crops[0].length();
        int[][] dp = new int[x][y];

        //initialilzation
        int i = 0;
        while (i < x) {
            Arrays.fill(dp[i], 0);
            i++;
        }
        
        //initializing Column 0
        i = 1;
        while (i < x) {
            dp[i][0] = dp[i-1][0];
            if(crops[i].charAt(0) != ' ' && crops[i].charAt(0) == crops[i-1].charAt(0)) {
                dp[i][0] = 1 + dp[i][0];
                String temp = crops[i];
                temp = ' ' + temp.substring(1);
                crops[i] = temp;
            }
            i++;
        }
        
        //initializing Row 0
        int j = 1;
        while (j < y) {
            dp[0][j] = dp[0][j-1];
            if(crops[0].charAt(j) != ' ' && crops[0].charAt(j) == crops[0].charAt(j-1)) {
                dp[0][j] = 1 + dp[0][j];
                String temp = crops[0];
                temp = temp.substring(0,j) + ' ' + temp.substring(j+1);
                crops[0] = temp;
            }
            j++;
        }
        
        i = 1;
        while (i < x) {
            for(j = 1; j < y; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
                if( (crops[i].charAt(j) == crops[i].charAt(j-1)) || (crops[i].charAt(j) == crops[i-1].charAt(j)) ){
                    dp[i][j] = 1 + dp[i][j];
                    String temp = crops[i];
                    temp = temp.substring(0,j) + ' ' + temp.substring(j+1);
                    crops[i] = temp;
                }
            }
            i++;
        }
        return dp[x-1][y-1];
    }
}