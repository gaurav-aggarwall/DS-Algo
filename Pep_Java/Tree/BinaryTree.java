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