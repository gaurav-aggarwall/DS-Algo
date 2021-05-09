import java.io.*;
import java.util.*;

public class StackClass {

  // Extra Brackets
  public static void extraBrackets(String str) {
    Stack<Character> st = new Stack<Character>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch != ')') {
        st.push(ch);
      } else {
        if (st.peek() == '(') {
          System.out.println(true);
          return;
        }
        while (st.peek() != '(') {
          st.pop();
        }
        st.pop();
      }
    }

    System.out.println(false);
  }

  // Balanced Bracket
  public static void balancedBracket(String str) {
    Stack<Character> st = new Stack<Character>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch == '(' || ch == '{' || ch == '[') {
        st.push(ch);
      } else if (ch == ')') {
        boolean valid = checkValid(st, '(');
        if (!valid) {
          System.out.println(false);
          return;
        }
      } else if (ch == '}') {
        boolean valid = checkValid(st, '{');
        if (!valid) {
          System.out.println(false);
          return;
        }
      } else if (ch == ']') {
        boolean valid = checkValid(st, '[');
        if (!valid) {
          System.out.println(false);
          return;
        }
      }
    }

    if (st.size() == 0)
      System.out.println(true);
    else
      System.out.println(false);
  }

  public static boolean checkValid(Stack<Character> st, char ch) {
    if (st.size() == 0 || st.peek() != ch)
      return false;
    else {
      st.pop();
      return true;
    }
  }

  // Next Greatest Integer
  // If stack is empty then ngr at i is -1
  // Otherwise if Peek is greater then that is ngr otherwise pop the elements till
  // peek is greater. If size is 0 after all pop then ngr is again -1
  public static int[] ngr(int[] arr) {
    int[] ans = new int[arr.length];
    Stack<Integer> st = new Stack<Integer>();

    for (int i = arr.length - 1; i >= 0; i--) {
      if (st.size() == 0) {
        ans[i] = -1;
      } else {
        if (st.peek() > arr[i]) {
          ans[i] = st.peek();
        } else {
          while (st.size() > 0 && st.peek() < arr[i]) {
            st.pop();
          }

          if (st.size() == 0) {
            ans[i] = -1;
          } else {
            ans[i] = st.peek();
          }
        }
      }
      st.push(arr[i]);
    }

    return ans;
  }


  public static void display(int[] a) {
    StringBuilder sb = new StringBuilder();

    for (int val : a) {
      sb.append(val + "\n");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = Integer.parseInt(br.readLine());
    }

    int[] nge = ngr(a);
    display(nge);
  }
}
