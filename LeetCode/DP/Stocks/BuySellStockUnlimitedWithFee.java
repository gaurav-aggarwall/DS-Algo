class BuySellStockUnlimitedWithFee {
  // Same as Unlimited Buy Sell but with after every complete transaction (buy AND sell) give a transaction fee
  // Either pay upfront - on day of buying or delay till day of selling

  // Recusrsion - TLE
  public int recusrive(int[] prices, int fee) {
    return recusrivehelper(0, true, prices, fee);
  }

  public int recusrivehelper(int i, boolean canBuy, int[] prices, int fee) {
    if (i == prices.length) return 0;

    if (canBuy) return Math.max(recusrivehelper(i + 1, true, prices, fee),                   // Still didnt buy
                                recusrivehelper(i + 1, false, prices) - prices[i], fee);     // Buy today - since buying price is gone from wallet(-)

    return Math.max(recusrivehelper(i + 1, false, prices, fee),                              // Still didnt sell
                    recusrivehelper(i + 1, true, prices, fee) + prices[i] - fee);            // Sell today - since selling price is added to wallet(+) and give fee
  }



  // Recusrsion with memoization
  public int recursiveMemoization(int[] prices, int fee) {
    Integer[][] dp = new Integer[prices.length][2];
    return recursiveMemoizationHelper(0, 1, prices, dp, fee);
  }

  public int recursiveMemoizationHelper(int i, int canBuy, int[] prices, Integer[][] dp, int fee) {
    if (i == prices.length) return 0;
    if (dp[i][canBuy] != null) return dp[i][canBuy]; 

    if (canBuy == 1) return dp[i][1] = Math.max(recursiveMemoizationHelper(i + 1, 1, prices, dp, fee),                   
                                                recursiveMemoizationHelper(i + 1, 0, prices, dp, fee) - prices[i]);
    
    return dp[i][0] = Math.max(recursiveMemoizationHelper(i + 1, 0, prices, dp, fee),                              
                               recursiveMemoizationHelper(i + 1, 1, prices, dp, fee) + prices[i] - fee);                  
  }



  // Tabulation
  public int tabulation(int[] prices, int fee) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][1] = Math.max(dp[i + 1][1], dp[i + 1][0] - prices[i]);            // buy
      dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1] + prices[i] - fee);      // sell
    }

    return dp[0][1];
  }



  // Tabulation with Space Optimization
  public int tabulationWithSpaceOptimization(int[] nums, int fee) {
    int[] nextDay = new int[2];
    int[] currDay = new int[2];

    for (int i = prices.length - 1; i >= 0; i--) {
      currDay[1] = Math.max(nextDay[1], nextDay[0] - prices[i]);          // buy
      currDay[0] = Math.max(nextDay[0], nextDay[1] + prices[i] - fee);    // sell

      nextDay = currDay;
    }

    return currDay[1];
  }
}