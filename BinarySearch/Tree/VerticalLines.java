// Approach -> Ans is width of tree.
//  when going left pass level - 1
// else in right pass level + 1
//  and update what is the max left and right we have traversed and as left is negative value ans is right - left + 1. 
// (1 is for root which is at 0 of number line)

import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class VerticalLines {
    int left = 0;
    int right = 0;

    public int solve(Tree root) {
        helper(root, 0);
        return right - left + 1;
    }
    
    void helper(Tree root, int level){
        if(root == null) return;
        
        if(level < left) left = level;
        if(level > right) right = level;

        helper(root.left, level-1);
        helper(root.right,level+1);
    }
}