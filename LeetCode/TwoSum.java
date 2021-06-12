import java.util.*;

class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (map.containsKey(target - num)) {
        return new int[] { map.get(target - num), i };
      } else {
        map.put(num, i);
      }
    }

    return new int[2];

  }
}