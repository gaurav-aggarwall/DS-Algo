class BuySellStock2Transactions {
  // Same as buy and sell, but with transaction cap - atmost 2 Transactions
  // 2 Transactions mean - Buy Sell Buy Sell
  // so to map this buy sell pattern we can use transaction id starting with 0
  // So Buy Sell Buy Sell -> 0 1 2 3
  // Here buys are even and sells are odd


  // Recusrsion - TLE
  public int recusrive(int[] prices) {
    return recusrivehelper(0, 0, prices);
  }

  public int recusrivehelper(int i, int transaction, int[] prices) {
    if (i == prices.length) return 0;
    if (transaction == 4) return 0;

    if (transaction % 2 == 0) return Math.max(recusrivehelper(i + 1, transaction, prices),                   // Still didnt buy
                                              recusrivehelper(i + 1, transaction + 1, prices) - prices[i]);  // Buy today - since buying price is gone from wallet(-)

    return Math.max(recusrivehelper(i + 1, transaction, prices),                       // Still didnt sell
                    recusrivehelper(i + 1, transaction + 1, prices) + prices[i]);      // Sell today - since selling price is added to wallet(+)
  }



  // Recusrsion with memoization
  public int recursiveMemoization(int[] prices) {
    Integer[][] dp = new Integer[prices.length][4];
    return recursiveMemoizationHelper(0, 0, prices, dp);
  }

  public int recursiveMemoizationHelper(int i, int transaction, int[] prices, Integer[][] dp) {
    if (i == prices.length) return 0;
    if (transaction == 4) return 0;

    if (dp[i][transaction] != null) return dp[i][transaction]; 

    if (transaction % 2 == 0) return dp[i][transaction] = Math.max(recursiveMemoizationHelper(i + 1, transaction, prices, dp),                   
                                                          recursiveMemoizationHelper(i + 1, transaction + 1, prices, dp) - prices[i]);
    
    return dp[i][transaction] = Math.max(recursiveMemoizationHelper(i + 1, transaction, prices, dp),                              
                                recursiveMemoizationHelper(i + 1, transaction + 1, prices, dp) + prices[i]);                  
  }



  // Tabulation
  public int tabulation(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][5];

    for (int i = n - 1; i >= 0; i--) {
      for (int transaction = 3; transaction >= 0; transaction--) {
        if (transaction % 2 == 0) dp[i][transaction] = Math.max(dp[i + 1][transaction], dp[i + 1][transaction + 1] - prices[i]);
        else dp[i][transaction] = Math.max(dp[i + 1][transaction], dp[i + 1][transaction + 1] + prices[i]);
      }
    }

    return dp[0][0];
  }



  // Tabulation with Space Optimization
  public int tabulationWithSpaceOptimization(int[] nums) {
    int[] nextDay = new int[5];
    int[] currDay = new int[5];

    for (int i = prices.length - 1; i >= 0; i--) {
      for (int transaction = 3; transaction >= 0; transaction--) {
        if (transaction % 2 == 0) currDay[transaction] = Math.max(nextDay[transaction], nextDay[transaction + 1] - prices[i]);
        else currDay[transaction] = Math.max(nextDay[transaction], nextDay[transaction + 1] + prices[i]);
      }

      nextDay = currDay;
    }

    return nextDay[0];
  }
}