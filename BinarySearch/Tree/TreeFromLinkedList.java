import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */

/**
 * class LLNode { int val; LLNode next; }
 */
class TreeFromLinkedList {
  public Tree solve(LLNode node) {
    if (node == null)
      return null;
    if (node.next == null)
      return new Tree(node.val);

    LLNode slow = node;
    LLNode fast = node;
    LLNode prev = node;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    Tree root = new Tree(slow.val);

    LLNode right = slow.next;
    prev.next = null;

    root.left = solve(node);
    root.right = solve(right);
    return root;
  }
}