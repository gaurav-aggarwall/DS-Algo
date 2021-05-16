import java.util.*;

class BananaPiles {
  
  // Approach -> FInd global maximum and then loop from 1 ... max to find ans
  // Ans is the smallest no which makes the piles 0 in k hours.
  // Instead of linear search use binary search to loop 
  // If it is possible at mid in Binary search then look for smaller ans in left of the range
  public int solve(int[] piles, int k) {
    int max = 0;
    for (int i = 0; i < piles.length; i++) {
      max = Math.max(max, piles[i]);
    }

    int ans = 0;

    int lo = 1;
    int hi = max;
    int mid = (lo + hi) / 2;
    
    while (lo <= hi) {
      mid = (lo + hi) / 2;
      if (ispossible(piles, k, mid)) {
        ans = mid;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }

    return ans;
  }

  boolean ispossible(int[] piles, int k, int r) {
    for (int i = 0; i < piles.length; i++) {
      if (k < 0) {
        break;
      }
      
      if (r >= piles[i]) {
        k--;
      } else {
        if (piles[i] % r == 0) {
          k -= piles[i] / r;
        } else {
          k -= piles[i] / r + 1;
        }
      }
    }
    return k >= 0;
  }
}