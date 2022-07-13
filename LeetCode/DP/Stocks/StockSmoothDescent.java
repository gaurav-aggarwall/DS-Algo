class StockSmoothDescent {
  // Since all elements in themselves are ans, hence fp array is init with 1
  // If ithe element is forms the ans with i-th array then dp[i] = 1 + dp[i-1]
  // IMP: Final ans is sum of dp array because:  
  // dp[i] is defined as the number of smooth descent periods of array ending at i
  // Now there are subarray ending at each i, hence sum of dp[i] gives req ans

  // Eg: [3,2,1,4]
  // dp[0] = 1  ->  [3]
  // dp[1] = 2  ->  [2] , [3,2]
  // dp[2] = 3  ->  [1] , [2,1] , [3,2,1]
  // dp[3] = 1  ->  [4]

  // Now each subarrays are ans hence summation
 
  // Tabulation
  public long tabulation(int[] prices) {
    int n = prices.length;
    long[] dp = new long[n];
    Arrays.fill(dp, 1L);
    long ans = 1L;

    for (int i = 1; i < n; i++) {
      if (prices[i - 1] - prices[i] == 1) dp[i] = 1L + dp[i - 1];
      ans += dp[i];
    }

    return ans;
  }

  // Tabulation with Space Optimization
  public long tabulationWithSpaceOptimization(int[] prices) {
    long prev = 1L, curr = 1L, ans = 1L;
    int n = prices.length;

    for(int i = 1; i < n; i ++) {
      if (prices[i - 1] - prices[i] == 1) curr = 1 + prev;
      
      ans += prev;
      prev = curr;
    }

    return ans;
  }
}