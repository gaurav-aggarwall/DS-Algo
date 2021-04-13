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