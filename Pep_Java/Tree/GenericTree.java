import java.io.*;
import java.util.*;

public class GenericTree {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  // Size Of tree
  public static int size(Node node){
    int s = 0;
    for(Node child : node.children) {
        s += size(child);
    }
    s += 1;
    return s;
  }

  // Max Of tree
  public static int max(Node node) {
    int m = Integer.MIN_VALUE;
    for (Node child : node.children) {
      m = Math.max(max(child), m);
    }
    m = Math.max(m, node.data);
    return m;
  }

  // Height of tree
  public static int height(Node node) {
    int h = -1;
    for(Node child : node.children) {
      h = Math.max(height(child), h);
    } 
    h += 1;
    return h;
  }

  // Traversals
  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);
    for (Node child : node.children) {
        System.out.println("Edge Pre " + node.data + "--" + child.data);
        traversals(child);
        System.out.println("Edge Post " + node.data + "--" + child.data);
    }
    System.out.println("Node Post " + node.data);
  }

  // Level Order - Single Line
  // Approach -> Add Root in queue, and till queue isn't empty , remove one and add its children and print
  public static void levelOrder(Node node){
    Queue<Node> q = new ArrayDeque<>();
    q.add(node);
    while(q.size() > 0) {
      node = q.remove();
      System.out.print(node.data + " "); 
      for(Node child: node.children){
        q.add(child);
      }
    }
    System.out.println(".");
  }

  // Level Order - Line Wise
  public static void levelOrderLinewise(Node node){
    Queue<Node> q = new ArrayDeque<>();
    q.add(node);

    while(q.size() > 0) {
      int s = q.size();
      for (int i = 0; i < s; i++) {
        node = q.remove();
        System.out.print(node.data + " ");
        for(Node child: node.children){
          q.add(child);
        }
      }
      System.out.println();
    }
  }

  // Level Order Traversal - Zig Zag
  // Approach :
  // 1. Maintain two stacks, main and curr level
  // 2. from main pop nodes and add their children in curr
  // 3. When main is empty it means this level is over increment level and make curr as new main and 
  //    curr as new stack
  // 4. When adding children to curr add in either normal or reverse order on basis of level
  //    If level is odd then normal and if even then reverse
  public static void levelOrderLinewiseZZ(Node node) {
    Stack<Node> main = new Stack<>();
    main.push(node);
    
    Stack<Node> curr = new Stack<>();
    int level = 1;
    
    while(main.size() > 0) {
      node = main.pop();
      System.out.print(node.data + " ");
      
      if (level % 2 == 1) {
        for(int i = 0; i < node.children.size(); i++) {
            Node childNode = node.children.get(i);
            curr.push(childNode);
        }  
      } else {
        for(int i = node.children.size() - 1; i >= 0; i--) {
            Node childNode = node.children.get(i);
            curr.push(childNode);
        }  
      }
      
      if(main.size() == 0) {
        main = curr;
        curr = new Stack<>();
        level++;
        System.out.println();
      }
    }
  }

  // Mirror
  // Approach -> Mirror all children and reverse the children
  public static void mirror(Node node){
    for (Node child : node.children) {
        mirror(child);
    }
    Collections.reverse(node.children);
  }

  // Remove Leaves
  public static void removeLeaves(Node node) {
    for(int i = node.children.size() - 1; i >= 0; i--) {
      if (node.children.get(i).children.size() == 0) {
        node.children.remove(i);
      } else {
        removeLeaves(node.children.get(i));
      } 
    }
  }

  // Linearize
  // Lineariz the children
  // Take last child and attach it at tail of second last child and remove last child
  public static void linearize(Node node){
    for (Node child : node.children) {
      linearize(child);
    }
    
    while (node.children.size() > 1) {
      Node last = node.children.remove(node.children.size() - 1);
      Node secondLast = node.children.get(node.children.size() - 1);
      Node secondLastTail = getTail(secondLast);
      secondLastTail.children.add(last);
    }
  }
  
  public static Node getTail(Node node) {
    while(node.children.size() == 1) {
      node = node.children.get(0);
    }
    return node;
  }

  // Find data in tree
  public static boolean find(Node node, int data) {
    if (node.data == data) return true;
    
    for (Node child : node.children) {
      if (find(child, data)) return true;     
    }
    
    return false;
  }

  // Node to Root path
  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    if (node.data == data) {
      ArrayList<Integer> ans = new ArrayList<Integer>();
      ans.add(data);
      return ans;
    }
    
    for(Node child : node.children) {
      ArrayList<Integer> ans = nodeToRootPath(child, data);
      if (ans.size() > 0) {
        ans.add(node.data);
        return ans;
      }
    }
    return new ArrayList<>();
 }

  // Lowest Common Ancestor (LCA)
  public static int lca(Node node, int d1, int d2) {
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);
    
    int a = p1.size() - 1;
    int b = p2.size() - 1;
    
    while(a >= 0 && b >= 0 && p1.get(a) == p2.get(b)) {
      a--;
      b--;
    }
    return p1.get(++a);
  }

  // Distance Between Two Nodes 
  public static int distanceBetweenNodes(Node node, int d1, int d2){
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);
    
    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }
    
    return i + j + 2;
  }

  // Are Trees similar in Shape
  public static boolean areSimilar(Node n1, Node n2) {
    if (n1 == null && n2 == null) return true;
    
    if (n1.children.size() != n2.children.size()) return false;
    
    for (int i = 0; i < n1.children.size(); i++) {
        boolean childResult = areSimilar(n1.children.get(i), n2.children.get(i));
        if (!childResult) return false;
    }
    
    return true;
  } 

  // Mirror
  public static boolean areMirror(Node n1, Node n2) {
    if (n1 == null && n2 == null) return true;
    
    int n1Size = n1.children.size();
    int n2Size = n2.children.size();
    
    if (n1Size != n2Size) return false;
    
    for (int i = 0; i < n1Size; i++) {
        boolean childResult = areMirror(n1.children.get(i), n2.children.get(n2Size - i - 1));
        if (!childResult) return false;
    }
    
    return true;
  }

  // Symmetric
  // Approach -> Only things which are mirror image of themselves are symmetric. Eg: Face
  public static boolean IsSymmetric(Node node) {
    return areMirror(node, node);
  }

  // Predecessor and Successor 
  // Approach -> 
  // State = 0 then if data matches then set state = 1 else this is the predecessor node
  // State = 1 Set successor as this will only be when we are on node just after 
  // we have already found the node as after this we set state = 2
  static Node predecessor;
  static Node successor;
  static int state;
  public static void predecessorAndSuccessor(Node node, int data) {
    if (state == 0) {
        if (node.data == data) {
            state = 1;
        } else {
            predecessor = node;
        }
    } else if (state == 1) {
        state = 2;
        successor = node;
    } 

    for (Node child : node.children) {
        predecessorAndSuccessor(child, data);
    }
  }

  // Ceil and Floor
  static int ceil;
  static int floor;
  public static void ceilAndFloor(Node node, int data) {
    if (node.data < data && node.data > floor) {
        floor = node.data;
    }
    
    if (node.data > data && node.data < ceil) {
        ceil = node.data;
    }
    
    for (Node child : node.children) {
        ceilAndFloor(child, data);
    }
  }

  // Kth Largest
  // Approach -> find floor k times starting with +Inf and now find floor with prev floor
  // on 1st iter -> floor will be max value  [ max = floor(+inf) ]
  // on 2nd iter -> floor will be second max  [ secondMax = floor(max) ]
  public static int kthLargest(Node node, int k){
    floor = Integer.MIN_VALUE;
    int ans = Integer.MAX_VALUE;
    
    for (int i = 0; i < k; i++) {
      ceilAndFloor(node, ans);
      ans = floor;
      floor = Integer.MIN_VALUE;
    }
    
    return ans;
  }

  // Max Subtree Sum
  static int maxSum;
  static Node maxNode;
  public static int maxSubtreeSum(Node node) {
    if (node == null) return 0;
    
    int childSum = 0;
    
    for(Node child : node.children) {
      childSum += maxSubtreeSum(child);
    }
    
    int sum = node.data + childSum;

    if (sum > maxSum) {
      maxSum = sum;
      maxNode = node;
    }
    
    return sum;
  }

  // Diameter
  // Approach -> This fun calculates dia but returns height
  // We need height because dia can be represented as
  // dia = deepest child height + second deepest child height + 2 
  // (2 because 2 edges to connect from one child to another through parent)
  static int dia = 0;
  public static int diameter(Node node) {
    int maxHt = -1;
    int secondMaxHt = -1;
    
    for (Node child : node.children) {
      int height = diameter(child);
      if (height > maxHt) {
        secondMaxHt = maxHt;
        maxHt = height;
      } else if (height > secondMaxHt) {
        secondMaxHt = height;
      }
    }
    
    if (maxHt + secondMaxHt + 2 > dia) {
      dia = maxHt + secondMaxHt + 2;
    } 
    maxHt += 1;
    return maxHt;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    int sz = size(root);
    System.out.println(sz);
  }

}