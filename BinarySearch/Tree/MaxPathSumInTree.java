import java.util.*;

/**
 * public class Tree { int val; Tree left; Tree right; }
 */
class Solution {
  public int solve(Tree root) {
    SumPair pair = sum(root);
    return pair.sum;
  }

  class SumPair {
    int maxSum;
    int sum;
  }

  public SumPair sum(Tree node) {
    if (node == null) {
      SumPair pair = new SumPair();
      pair.maxSum = 0;
      pair.sum = 0;
      return pair;
    }

    SumPair left = sum(node.left);
    SumPair right = sum(node.right);

    SumPair pair = new SumPair();

    pair.maxSum = Math.max(node.val, Math.max(left.maxSum, right.maxSum) + node.val);

    pair.sum = Math.max(node.val, Math.max(Math.max(right.maxSum, left.maxSum) + node.val,
        Math.max(left.maxSum + right.maxSum + node.val, Math.max(left.sum, right.sum))));

    return pair;
  }
}