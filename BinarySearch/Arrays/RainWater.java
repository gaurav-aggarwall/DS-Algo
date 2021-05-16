import java.util.*;

class RainWater {

	// Approach 1 - Unoptimized
	// Any water accumulation at any point will be done on basis of min of left max and right max
	// and excluding the curent height to get the total height of water
	public int solver1(int[] nums) {
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];

		int lmax = 0;
		for (int i = 0; i < nums.length; i++) {
			lmax = Math.max(lmax, nums[i]);
			left[i] = lmax;
		}

		int rmax = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			rmax = Math.max(rmax, nums[i]);
			right[i] = rmax;
		}

		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			ans += Math.min(right[i], left[i]) - nums[i];
		}

		return ans;
	}

	// Approach 2 - Optimized
	// After global max is found, if we divide in two sides, water accumulation only depends on
	// local maxima as there is global maxima is presen already, for left array in right side and
	// for right array in left side. 
	public int solver2(int[] nums) {
		int ans = 0;

		// Global maximum
		int max = -1;
		int max_idx = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max) {
				max_idx = i;
				max = nums[i];
			}
		}

		// Left local maximums
		int l_max = -1;
		for (int i = 0; i < max_idx; i++) {
			if (nums[i] > l_max) {
				l_max = nums[i];
			} else {
				ans += l_max - nums[i];
			}
		}

		// right local maximum
		int r_max = -1;
		for (int i = nums.length - 1; i > max_idx; i--) {
			if (nums[i] > r_max) {
				r_max = nums[i];
			} else {
				ans += r_max - nums[i];
			}
		}

		return ans;
	}

}