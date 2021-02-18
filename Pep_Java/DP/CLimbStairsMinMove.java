import java.util.*;

public class CLimbStairsMinMove {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        
        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        
        Integer[] dp = new Integer[n + 1];
        dp[n] = 0;
        
        for(int i = n - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                int steps = Integer.MAX_VALUE;
            
                for(int j = 1; j <= arr[i]; j++) {
                    if(i + j < dp.length &&  dp[i + j] != null) {
                        steps = Math.min(steps, dp[i+j]);
                    }
                }
                
                if(steps != Integer.MAX_VALUE) {
                    dp[i] = steps + 1;
                }   
            }
        }
        
        System.out.println(dp[0]);
        
        sc.close();
    }

}