class HouseRobber {
  // Either pick the current ith house and calls for i - 2 house 
  // or calls for i - 1 house

  // Recusrsion - TLE
  public int recusrive(int[] nums) {
    return recusrivehelper(nums.length - 1, nums);
  }

  public int recusrivehelper(int i, int[] nums) {
    if (i == 0) return nums[0];
    if (i < 0) return 0;

    return Math.max(nums[i] + recusrivehelper(i - 2, nums),        // Pick 
                    recusrivehelper(i - 1, nums));                 // Non Pick
  }



  // Recusrsion with memoization
  public int recursiveMemoization(int[] nums) {
    Integer[] dp = new Integer[nums.length];
    return recursiveMemoizationHelper(nums.length - 1, nums, dp);
  }

  public int recursiveMemoizationHelper(int i, int[] nums, Integer[] dp) {
    if (i == 0) return nums[0];
    if (i < 0) return 0;

    if (dp[i] != null) return dp[i];

    return dp[i] = Math.max(nums[i] + recursiveMemoizationHelper(i - 2, nums, dp),     // Pick
                            recursiveMemoizationHelper(i - 1, nums, dp));              // Non Pick
  }



  // Tabulation
  public int tabulation(int[] nums) {
    Integer[] dp = new Integer[nums.length];

    dp[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int pick = nums[i];
      if (i > 1) pick += dp[i - 2];
      int nonPick = dp[i - 1];

      dp[i] = Math.max(pick, nonPick); 
    }

    return dp[nums.length - 1];
  }



  // Tabulation with Space Optimization
  public int tabulationWithSpaceOptimization(int[] nums) {
		int last = nums[0];
		int secondLast = 0;

		for (int i = 1; i < nums.length; i++) {
      int pick = nums[i];
      if (i > 1) pick += secondLast;
      int nonPick = last;

      secondLast = last;
      last = Math.max(pick , nonPick);  
		}

		return last;
  }
}