class BuySellStockUnlimitedCooldown {
  // Same as Unlimited Buy Sell but with after sell there is cooldown period of 1 day.
  // since we are selling and cooldown of 1 we directly go to i + 2 instead of i + 1 
  // also create DP of n + 2 instead of n + 1 to avoid overflow

  // Tabulation
  public int tabulation(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 2][2];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][1] = Math.max(dp[i + 1][1], dp[i + 1][0] - prices[i]);        // buy
      dp[i][0] = Math.max(dp[i + 1][0], dp[i + 2][1] + prices[i]);        // sell
    }

    return dp[0][1];
  }
}