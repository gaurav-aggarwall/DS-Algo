import java.io.*;
import java.util.*;

public class BST {
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

	// Size
  public static int size(Node node) {
    if (node == null) return 0;
    return size(node.left) + size(node.right) + 1;
  }

	// Sum
  public static int sum(Node node) {
    if (node == null) return 0;
    return sum(node.left) + sum(node.right) + node.data;
  }

	// Max
  public static int max(Node node) {
    if (node == null) return 0;
    if (node.right == null) return node.data;
    else return max(node.right);
  }

	// Min
  public static int min(Node node) {
    if (node == null) return 0;
    if (node.left == null) return node.data;
    else return min(node.left);
  }

	// Find
  public static boolean find(Node node, int data){
    if (node == null) return false;
    
    if (node.data == data) return true;
    else if (node.data > data) return find(node.left, data);
    else if (node.data < data) return find(node.right, data);

    return false;
  }  

	// Add New Node in BST
	// Traverse appropriately, when null is encounterd, create new node and return and attach to calling node accordingly.
	public static Node add(Node node, int data) {
    if (node == null) return new Node(data, null, null);
    
    if (node.data > data) node.left = add(node.left, data);
    if (node.data < data) node.right = add(node.right, data);
    
    return node;
  }

	// Remove Node in BST
	// If node have no child return nulll
	//  If node has one child, return child
	//  If node has two children, change node's value by max of its left sub tree and remove that max node 
	public static Node remove(Node node, int data) {
    if (node == null) return node;
    
    if (node.data == data) {
      if (node.left == null && node.right == null) {
        return null;
      } else if (node.left == null || node.right == null) {
        return node.left == null ? node.right : node.left;
      } else {
        int lMax = max(node.left);
        node.data = lMax;
        node.left = remove(node.left, lMax);
      }
    } else if (node.data > data) {
      node.left = remove(node.left, data);
    } else {
      node.right = remove(node.right, data);
    }
    
    return node;
  }

	// Replace with Right Larger Sum
	static int sum = 0;
  public static void rwsol(Node node){
    if (node == null) return;

    rwsol(node.right);
    
    int temp = node.data;
    node.data = sum;
    sum += temp;
    
    rwsol(node.left);
  }

	// LCA 
	// If d1 and d2 are on different sides, then current node (root) is lca, otherwise cal appr.
	public static int lca(Node node, int d1, int d2) {
    if (node == null) return 0;
    
    if (node.data > d1 && node.data > d2) return lca(node.left, d1, d2);
    else if (node.data < d1 && node.data < d2) return lca(node.right, d1, d2);
    else return node.data; 
  }

	// Print In Range
  // If d1 and d2 are on different side, then call recursively for left to print then node and then print right,
  // otherwise call appr.
  public static void pir(Node node, int d1, int d2) {
    if (node == null) return;
    
    if (node.data > d1 && node.data > d2) pir(node.left, d1, d2);
    else if (node.data < d1 && node.data < d2) pir(node.right, d1, d2);
    else {
      pir(node.left, d1, d2);
      System.out.println(node.data);
      pir(node.right, d1, d2);
    }
  }

	// Target Sum Pair
  static ArrayList<Integer> nodes;
  public static void getNodes(Node node) {
    if (node == null) return;
    
    getNodes(node.left);
    nodes.add(node.data);
    getNodes(node.right);
  }
  
  public static void printTargetSum(Node root, int data) {
    nodes = new ArrayList<>();
    getNodes(root);
    int left = 0;
    int right = nodes.size() - 1;
    
    while(left < right) {
      int sum = nodes.get(left) + nodes.get(right);
      if (sum == data) {
        System.out.println(nodes.get(left) + " " + nodes.get(right));
        left++;
        right--; 
      } else if (sum < data) {
        left++;
      } else {
        right--;
      }
    }
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

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);

    int size = size(root);
    int sum = sum(root);
    int max = max(root);
    int min = min(root);
    boolean found = find(root, data);

    System.out.println(size);
    System.out.println(sum);
    System.out.println(max);
    System.out.println(min);
    System.out.println(found);
  }

}