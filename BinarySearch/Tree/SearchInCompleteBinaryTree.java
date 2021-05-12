import java.util.*;

/**
 * public class Tree { int val; Tree left; Tree right; }
 */
class SearchInCompleteBinaryTree {
  public boolean solve(Tree root, int target) {
    if (root == null)
      return false;

    if (root.val == target)
      return true;

    boolean gr = goRight(root.val, target);

    if (gr)
      return solve(root.right, target);
    else
      return solve(root.left, target);
  }

  public static boolean goRight(int val, int target) {
    int left = val * 2;
    int right = left + 1;

    while (target > right) {
      target /= 2;
    }

    return target == right;
  }

}