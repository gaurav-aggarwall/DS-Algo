import java.util.*;

/**
 * public class Tree { int val; Tree left; Tree right; }
 */
class ConstructPreIn {
  public Tree solve(int[] preorder, int[] inorder) {
    return solveHelper(preorder, inorder, 0, inorder.length - 1);
  }

  int pi = 0;

  public Tree solveHelper(int[] preorder, int[] inorder, int s, int e) {
    if (e < s)
      return null;

    Tree root = new Tree(preorder[pi++]);

    int index = getIndex(inorder, s, e, root.val);

    root.left = solveHelper(preorder, inorder, s, index - 1);
    root.right = solveHelper(preorder, inorder, index + 1, e);

    return root;
  }

  public int getIndex(int[] inorder, int s, int e, int val) {
    for (int i = s; i <= e; i++) {
      if (inorder[i] == val)
        return i;
    }
    return 0;
  }
}