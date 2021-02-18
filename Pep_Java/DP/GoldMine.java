import java.util.*;

public class GoldMine {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] arr = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[n][m];
        
        for(int j = dp[0].length - 1; j >= 0; j--) {
            for(int i = 0; i < dp.length; i++) {
                if (j == dp[0].length - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == 0) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j + 1]) + arr[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i - 1][j + 1]) + arr[i][j];
                } else {
                    int n1 = Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
                    int n2 = Math.max(n1, dp[i + 1][j + 1]);
                    dp[i][j] = n2 + arr[i][j];
                }
            }
        }
        
        int ans = dp[0][0];
        
        for(int i = 1; i < dp.length; i++) {
            if(dp[i][0] > ans) {
                ans = dp[i][0];
            }
        }
        
        System.out.println(ans);

        sc.close();
    }

}