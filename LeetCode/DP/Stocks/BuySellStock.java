class BuySellStock {
  // At any index check the profit and update max profit 
  // For Max profit, Sell Highest and Buy Lowest
  // So find min buy till curr index and max profit till curr index

  public int maxProfit(int[] prices) {
    int buy = Integer.MAX_VALUE, profit = 0;
    for (int i = 0; i < prices.length; i++){
      buy = Math.min(buy, prices[i]);
      profit = Math.max(profit, prices[i] - buy);
    }
    return profit;
  }
}