import java.util.*;

/**
 * class LLNode { int val; LLNode next; }
 */
class RemoveRangeAndConcat {
  public LLNode solve(LLNode a, LLNode b, int lo, int hi) {
    LLNode dummy = new LLNode(0);
    LLNode ans = dummy;
    dummy.next = a;

    int tx = 0;
    while (tx < lo) {
      dummy = dummy.next;
      tx++;
    }

    LLNode end = dummy.next;

    while (tx <= hi) {
      end = end.next;
      tx++;
    }

    dummy.next = b;
    while (dummy.next != null)
      dummy = dummy.next;
    dummy.next = end;

    return ans.next;
  }
}
