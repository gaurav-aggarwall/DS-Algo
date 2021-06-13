class KReverseLL {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode ans = new ListNode(-1);
    ListNode curr = ans;

    while (head != null) {
      ListNode temp = head;
      ListNode prev = null;

      boolean found = false;

      for (int i = 0; i < k; i++) {
        if (head != null) {
          prev = head;
          head = head.next;
        } else {
          found = true;
          break;
        }
      }

      if (found) {
        curr.next = temp;
        break;
      } else {
        prev.next = null;
        curr.next = reverse(temp);
        curr = temp;
      }
    }
    return ans.next;
  }

  public ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;

      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }
}
