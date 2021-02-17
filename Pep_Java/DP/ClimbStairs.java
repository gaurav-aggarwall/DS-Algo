import java.util.*;

public class ClimbStairs {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int[] dp = new int[n + 1];
        dp[n] = 1;
        
        for (int i = dp.length - 2; i >= 0; i--) {
            if (i + 1 < dp.length) dp[i] +=  dp[i+1];
            if (i + 2 < dp.length) dp[i] +=  dp[i+2];
            if (i + 3 < dp.length) dp[i] +=  dp[i+3];
        }
        
        System.out.println(dp[0]);
        
        sc.close();
    }

}