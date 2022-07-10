class HouseRobber {
  // Since Array is circular, both 0 th and n-1 th index can not be included.
  // Call for o to n - 2 and 1 to n - 1 and check for max
  // Either pick the current ith house and calls for i - 2 house 
  // or calls for i - 1 house


  // Recusrsion - TLE
  public int recusrive(int[] nums) {
    if (nums.length == 1) return nums[0];

    return Math.max(recusrivehelper(0, nums.length - 2, nums),      // 0 to n - 2
                    recusrivehelper(1, nums.length - 1, nums));     // 1 to n - 1
  }

  public int recusrivehelper(int s, int i, int[] nums) {
    if (i == s) return nums[s];
    if (i < s) return 0;

    return Math.max(nums[i] + recusrivehelper(s, i - 2, nums),        // Pick 
                    recusrivehelper(s, i - 1, nums));                 // Non Pick
  }



  // Recusrsion with memoization
  public int recursiveMemoization(int[] nums) {
    if (nums.length == 1) return nums[0];

    int[] first = new int[nums.length - 1];
    int[] last = new int[nums.length - 1];
    
    for (int i = 0; i < nums.length; i++) {
      if (i != 0) last[i - 1] = nums[i];
      if (i != nums.length - 1) first[i] = nums[i];
    } 

    return Math.max(recursiveMemoizationHelper(first.length - 1, first, new Integer[first.length]),      // 0 to n - 2
                    recursiveMemoizationHelper(last.length - 1, last, new Integer[last.length]));        // 1 to n - 1
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
    if (nums.length == 1) return nums[0];

    int[] first = new int[nums.length - 1];
    int[] last = new int[nums.length - 1];
    
    for (int i = 0; i < nums.length; i++) {
      if (i != 0) last[i - 1] = nums[i];
      if (i != nums.length - 1) first[i] = nums[i];
    } 

    return Math.max(tabulationHelper(first), tabulationHelper(last));
  }

  public int tabulationHelper(int[] nums) {
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
		if (nums.length == 1) return nums[0];

    int[] first = new int[nums.length - 1];
    int[] last = new int[nums.length - 1];
    
    for (int i = 0; i < nums.length; i++) {
      if (i != 0) last[i - 1] = nums[i];
      if (i != nums.length - 1) first[i] = nums[i];
    } 

    return Math.max(tabulationHelper(first), tabulationHelper(last));
  }

  public int tabulationWithSpaceOptimizationHelper(int[] nums) {
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