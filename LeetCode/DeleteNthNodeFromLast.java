class DeleteNthNodeFromLast {
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

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode aptr = head;
    ListNode bptr = head;
    ListNode prev = null;

    while (n > 0 && aptr != null) {
      aptr = aptr.next;
      n--;
    }

    while (aptr != null) {
      aptr = aptr.next;
      prev = bptr;
      bptr = bptr.next;
    }

    if (bptr == null) {
      return head;
    }

    if (bptr == head) {
      return head.next;
    } else {
      prev.next = bptr.next;
      return head;
    }
  }
}