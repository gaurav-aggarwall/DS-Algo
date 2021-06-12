public class atoi {
  public int myAtoi(String s) {
    s = s.trim();

    if (s == null || s.length() == 0)
      return 0;

    int sign = 1, ans = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (i == 0 && (c == '-' || c == '+')) {
        sign = c == '-' ? -1 : 1;
        continue;
      }

      int temp = c - '0';

      if (temp < 0 || temp > 9)
        break;

      if ((Integer.MAX_VALUE - temp) / 10 < ans) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      ans = ans * 10 + temp;
    }

    return ans * sign;
  }
}
