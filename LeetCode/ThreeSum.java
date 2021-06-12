import java.util.*;

public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
      int lower = i + 1;
      int higher = nums.length - 1;

      if (i > 0 && nums[i - 1] == nums[i])
        continue;

      while (lower < higher) {
        int sum = nums[i] + nums[lower] + nums[higher];
        if (sum == 0) {
          result.add(List.of(nums[i], nums[lower], nums[higher]));
          while (lower < nums.length && nums[lower - 1] == nums[lower])
            lower += 1;
          while (higher > 0 && nums[higher] == nums[higher + 1])
            higher -= 1;
          higher -= 1;
          lower += 1;
        }

        if (sum > 0) {
          higher -= 1;
        } else if (sum < 0) {
          lower += 1;
        }
      }
    }
    return result;
  }
}
