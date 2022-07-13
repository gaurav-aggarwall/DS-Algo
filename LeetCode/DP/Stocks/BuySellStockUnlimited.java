class BuySellStockUnlimited {
  // Either pick the current ith house and calls for i - 2 house 
  // or calls for i - 1 house

  // Recusrsion - TLE
  public int recusrive(int[] prices) {
    return recusrivehelper(0, true, prices);
  }

  public int recusrivehelper(int i, boolean canBuy, int[] prices) {
    if (i == prices.length) return 0;

    if (canBuy) return Math.max(recusrivehelper(i + 1, true, prices),                   // Still didnt buy
                                recusrivehelper(i + 1, false, prices) - prices[i]);     // Buy today - since buying price is gone from wallet(-)

    return Math.max(recusrivehelper(i + 1, false, prices),                              // Still didnt sell
                    recusrivehelper(i + 1, true, prices) + prices[i]);                  // Sell today - since selling price is added to wallet(+)
  }



  // Recusrsion with memoization
  public int recursiveMemoization(int[] prices) {
    Integer[][] dp = new Integer[prices.length][2];
    return recursiveMemoizationHelper(0, 1, prices, dp);
  }

  public int recursiveMemoizationHelper(int i, int canBuy, int[] prices, Integer[][] dp) {
    if (i == prices.length) return 0;
    if (dp[i][canBuy] != null) return dp[i][canBuy]; 

    if (canBuy == 1) return dp[i][1] = Math.max(recursiveMemoizationHelper(i + 1, 1, prices, dp),                   
                                                recursiveMemoizationHelper(i + 1, 0, prices, dp) - prices[i]);
    
    return dp[i][0] = Math.max(recursiveMemoizationHelper(i + 1, 0, prices, dp),                              
                               recursiveMemoizationHelper(i + 1, 1, prices, dp) + prices[i]);                  
  }



  // Tabulation
  public int tabulation(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][1] = Math.max(dp[i + 1][1], dp[i + 1][0] - prices[i]);        // buy
      dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1] + prices[i]);        // sell
    }

    return dp[0][1];
  }



  // Tabulation with Space Optimization
  public int tabulationWithSpaceOptimization(int[] nums) {
    int[] nextDay = new int[2];
    int[] currDay = new int[2];

    for (int i = prices.length - 1; i >= 0; i--) {
      currDay[1] = Math.max(nextDay[1], nextDay[0] - prices[i]);        // buy
      currDay[0] = Math.max(nextDay[0], nextDay[1] + prices[i]);        // sell

      nextDay = currDay;
    }

    return currDay[1];
  }
}