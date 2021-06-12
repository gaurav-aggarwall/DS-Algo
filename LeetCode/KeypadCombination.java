import java.util.*;

class KeypadCombination {
  String[] codes = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0)
      return new ArrayList<String>();

    return helper(digits);
  }

  public List<String> helper(String str) {
    if (str.length() == 0) {
      List<String> baseCaseList = new ArrayList<>();
      baseCaseList.add("");
      return baseCaseList;
    }

    char startingChar = str.charAt(0);
    String nextRec = str.substring(1);
    String code = codes[startingChar - '2'];

    List<String> list = helper(nextRec);
    List<String> ans = new ArrayList<>();

    for (int i = 0; i < code.length(); i++) {
      char ch = code.charAt(i);

      for (String val : list) {
        ans.add(ch + val);
      }
    }
    return ans;
  }
}