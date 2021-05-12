import java.util.*;

/**
 * public class Tree { int val; Tree left; Tree right; }
 */
class CountNodesInCompleteBinaryTree {
  public int height(Tree node, boolean left) {
    if (node == null)
      return 0;

    int h = 1;

    if (left) {
      while (node.left != null) {
        node = node.left;
        h++;
      }
    } else {
      while (node.right != null) {
        node = node.right;
        h++;
      }
    }

    return h;
  }

  public int solve(Tree root) {
    if (root == null)
      return 0;

    int lh = height(root, true);
    int rh = height(root, false);

    if (lh == rh)
      return (int) Math.pow(2, lh) - 1;

    return solve(root.left) + solve(root.right) + 1;
  }
}