import java.io.*;
import java.util.*;

public class BinaryTree {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  // Level Order
  // Add root in queue, in each step take size and for each iter, remove the node in queue print and add its child
  public static void levelOrder(Node node) {
    if (node == null) return;

    Queue<Node> q = new ArrayDeque<>();
    q.add(node);

    while(q.size() > 0) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        Node n = q.remove();
        System.out.print(n.data + " ");
        
        if (n.left != null) q.add(n.left);
        if (n.right != null) q.add(n.right);
      }

      System.out.println();
    }
  }

  // Iterative Pre Post and In order
  // Push new pair with state 0 and root node . 
  // If state is 0, this is preorder and add in pre string. Also Add its left child in stack and increment the state
  // If state is 1, this is inorder and add in inorder string. Also Add its right child in stack and increment the state
  // If state is 2, this post order and add in post string and pop the pair node. 
  public static void iterativePrePostInTraversal(Node node) {
    if (node == null) return;
  
    String pre = "";    // State -> 0
    String in = "";     // State -> 1
    String post = "";   // State -> 2
    
    Stack<Pair> st = new Stack<>();
    st.push(new Pair(node, 0));
    
    while(st.size() > 0) {
      Pair p = st.peek();
      
      if (p.state == 0) {
        pre += p.node.data + " ";
        p.state++;
        
        if (p.node.left != null) st.push(new Pair(p.node.left, 0));
      } else if (p.state == 1) {
        in += p.node.data + " ";
        p.state++;
        
        if (p.node.right != null) st.push(new Pair(p.node.right, 0));
      } else {
        post += p.node.data + " ";
        st.pop();
      }
    }
    
    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
  }

  // Find
  public static boolean find(Node node, int data){
    if (node == null) return false;
    
    if (node.data == data) return true;
    
    boolean left = find(node.left, data);
    if (left) return true;
    
    boolean right = find(node.right, data);
    if (right) return true;
    
    return false; 
  }

  // Node To Root Path
  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    if (node == null) return new ArrayList<>();
    
    if (find(node, data) == false) return new ArrayList<>();
    
    if (node.data == data) {
      ArrayList<Integer> path = new ArrayList<>();
      path.add(node.data);
      return path;
    } 
    
    ArrayList<Integer> leftPath = nodeToRootPath(node.left, data);
    if (leftPath.size() > 0) {
      leftPath.add(node.data);
      return leftPath;
    }
    
    ArrayList<Integer> rightPath = nodeToRootPath(node.right, data);
    if (rightPath.size() > 0) {
      rightPath.add(node.data);
      return rightPath;
    }
    
    return null;
  }

  // Print K Levels Down
  public static void printKLevelsDown(Node node, int k){
    if (node == null) return;
    
    if (k == 0) {
        System.out.println(node.data);
        return;
    }
    
    printKLevelsDown(node.left, k - 1);
    printKLevelsDown(node.right, k - 1);
  }

  // Print K Levels Down With Blocker
  public static void printKLevelsDown(Node node, int k, Node blocker){
    if (node == null || node == blocker) return;
    
    if (k == 0) {
        System.out.println(node.data);
        return;
    }
    
    printKLevelsDown(node.left, k - 1, blocker);
    printKLevelsDown(node.right, k - 1, blocker);
  }

  // IMP: Prink K nodes away
  // Approach: Find Node To Root Path. Loop over all the nodes in node to root path
  // Now we have to find k leels down from each of them but with different k levels for each
  // For i = 0, levels will be k, because its same ques node
  // For i = 1, levels will be k - 1 as it is already 1 level away from ques node. Therefore k - i is general
  // Trick: We have to use blocker to not print in the blocker direction. As we are coming up from right then print 
  // k - i level in left only. So For each iter, Blocker node is just prev node in node to root path  
  public static void printKNodesFar(Node node, int data, int k) {
    if (node == null) return;
    
    ArrayList<Node> path = nodeToRootPath(node, data);
    
    for(int i = 0; i < path.size() && i <= k; i++) {
        Node blocker = null;
        if (i > 0) blocker = path.get(i - 1);
        printKLevelsDownWithBlocker(path.get(i), k - i, blocker);
    }
  }

  // Path to Leaf Node with Range
  public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
    if (node == null) return;
    
    if (node.left == null && node.right == null) {
        sum += node.data;
        if (sum >= lo && sum <= hi) System.out.println(path + node.data + " ");
        return;
    }
    
    pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
    pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
  }

  // Left Cloned Tree
  public static Node createLeftCloneTree(Node node){
    if (node == null) return null;
    
    Node leftTree = createLeftCloneTree(node.left);
    Node rightTree = createLeftCloneTree(node.right);
    
    node.right = rightTree;
    node.left = new Node(node.data, null, null);
    node.left.left = leftTree;
    
    return node;
  }

  // Transform back from left cloned tree
  public static Node transBackFromLeftClonedTree(Node node){
    if (node == null) return null;
    
    node.left = transBackFromLeftClonedTree(node.left.left);
    node.right = transBackFromLeftClonedTree(node.right);
    
    return node;
  }
  
  // Print Single Child Nodes
  public static void printSingleChildNodes(Node node, Node parent){
    if (node == null) return;
    
    if (parent != null && (parent.left == null || parent.right == null)) {
        System.out.println(node.data);
    }
    
    printSingleChildNodes(node.left, node);
    printSingleChildNodes(node.right, node);
  }

  // Remove Leaf Nodes
  public static Node removeLeaves(Node node){
    if (node == null || (node.left == null && node.right == null)) return null;
    
    node.left = removeLeaves(node.left);
    node.right = removeLeaves(node.right);
    
    return node;
  }

  // Diameter
  // From every node return DiaPair
  // Height in terms of edges is max of left-height and right-height + 1
  // Dia can be either in left only, right only or via root
  // Therfore it is max of left dia and right dia, which is then compared with leftHeight + rightHEight + 2 
  static class DiaPair {
    int height;
    int dia;
  }
  
  public static DiaPair diameter1(Node node) {
    if (node == null) {
        DiaPair dp = new DiaPair();
        dp.height = -1;
        dp.dia = 0;
        return dp;
    }  
      
    DiaPair left = diameter1(node.left);
    DiaPair right = diameter1(node.right);
    
    DiaPair dp = new DiaPair();
    dp.height = Math.max(left.height, right.height) + 1;
    dp.dia = Math.max(left.height + right.height + 2, Math.max(left.dia, right.dia));
    
    return dp;
  }
  
  // Tilt
  static int tilt = 0;
  public static int tilt(Node node){
    if (node == null) return 0;
    
    int leftSum = tilt(node.left);
    int rightSum = tilt(node.right);
    
    tilt += Math.abs(leftSum - rightSum);
    return node.data + leftSum + rightSum;
  } 

  // Is Tree A BST
  // Approach -> For a tree to be BST, left and right tree to be BST
  // And node value should be greater than max value in left tree and less than min value in right tree
  public static class BSTPair {
    boolean isBST = true;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
  }
  
  public static BSTPair isTreeBST(Node node) {
    if (node == null) return new BSTPair();  
    
    BSTPair lp = isTreeBST(node.left);
    BSTPair rp = isTreeBST(node.right);
    
    BSTPair pair = new BSTPair();
    pair.min = Math.min(node.data, Math.min(lp.min, rp.min));
    pair.max = Math.max(node.data, Math.max(lp.max, rp.max));
    if (lp.isBST == false || rp.isBST == false) {
      pair.isBST = false;
    } else {
      pair.isBST = node.data >= lp.max && node.data <= rp.min;
    }
    return pair;
  }

  // Is Tree Balanced
  static boolean isBalanced = true;
  public static int isTreeBalanced(Node node) {
    if (node == null || isBalanced == false) return 0;
    
    int leftHeight = isTreeBalanced(node.left);
    int rightHeight = isTreeBalanced(node.right);
    
    if (Math.abs(leftHeight - rightHeight) > 1) isBalanced = false;
    return Math.max(leftHeight, rightHeight) + 1;
  }

  // Largest BST in tree
  public static class BstPair {
    boolean isBST = true;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    Node maxBST = null;
    int size = 0;
  }
  
  public static BstPair largestBST(Node node) {
    if (node == null) return new BstPair();  
    
    BstPair lp = largestBST(node.left);
    BstPair rp = largestBST(node.right);
    
    BSTPair pair = new BstPair();
    pair.min = Math.min(node.data, Math.min(lp.min, rp.min));
    pair.max = Math.max(node.data, Math.max(lp.max, rp.max));
    if (lp.isBST == false || rp.isBST == false) {
      pair.isBST = false;
    } else {
      pair.isBST = node.data >= lp.max && node.data <= rp.min;
    }
    
    if (pair.isBST) {
        pair.size = lp.size + rp.size + 1;
        pair. maxBST = node;
    } else if (lp.size > rp.size) {
        pair.size = lp.size;
        pair.maxBST = lp.maxBST;
    } else {
        pair.size = rp.size;
        pair.maxBST = rp.maxBST;
    }
    
    return pair;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    levelOrder(root);
  }

}