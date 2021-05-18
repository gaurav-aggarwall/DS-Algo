class SmallestLargePair {
  public int solve(int[] nums, int target) {
    int ans = Integer.MAX;
    Arrays.sort(nums);

    int l = 0;
    int r = nums.length - 1;

    while (l < r) {
      if (nums[l] + nums[r] <= target) {
        l++;
      } else {
        ans = Math.min(ans, nums[l] + nums[r]);
        r--;
      }
    }
    
    return ans;
  }
}