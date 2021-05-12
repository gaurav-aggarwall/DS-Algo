import java.util.*;

class PairSumInBST {
  public boolean solve(Tree a, Tree b, int targ) {
    if (a == null || b == null)
      return false;
    if (a.val + b.val == targ)
      return true;
    if (a.val + b.val > targ)
      return solve(a.left, b, targ) || solve(a, b.left, targ);
    else
      return solve(a.right, b, targ) || solve(a, b.right, targ);
  }
}