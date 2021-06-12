import java.util.*;

public class LongestSubstringWithoutRepeat {
  public int lengthOfLongestSubstring(String str) {
    int n = str.length();

    int res = 0;

    int[] lastIndex = new int[256];
    Arrays.fill(lastIndex, -1);

    int i = 0;

    for (int j = 0; j < n; j++) {
      i = Math.max(i, lastIndex[str.charAt(j)] + 1);
      res = Math.max(res, j - i + 1);
      lastIndex[str.charAt(j)] = j;
    }

    return res;
  }
}
