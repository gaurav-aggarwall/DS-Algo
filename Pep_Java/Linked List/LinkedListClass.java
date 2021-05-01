import java.io.*;

public class LinkedListClass {
  public static class Node {
    int data;
    Node next;
  }

  public static class LinkedList {
    Node head;
    Node tail;
    int size;

    void addLast(int val) {
      Node temp = new Node();
      temp.data = val;
      temp.next = null;

      if (size == 0) {
        head = tail = temp;
      } else {
        tail.next = temp;
        tail = temp;
      }

      size++;
    }

    public int size() {
      return size;
    }

    public void display() {
      for (Node temp = head; temp != null; temp = temp.next) {
        System.out.print(temp.data + " ");
      }
      System.out.println();
    }

    public void removeFirst() {
      if (size == 0) {
        System.out.println("List is empty");
      } else if (size == 1) {
        head = tail = null;
        size = 0;
      } else {
        head = head.next;
        size--;
      }
    }

    public int getFirst() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return head.data;
      }
    }

    public int getLast() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return tail.data;
      }
    }

    public int getAt(int idx) {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else if (idx < 0 || idx >= size) {
        System.out.println("Invalid arguments");
        return -1;
      } else {
        Node temp = head;
        for (int i = 0; i < idx; i++) {
          temp = temp.next;
        }
        return temp.data;
      }
    }

    public void addFirst(int val) {
      Node temp = new Node();
      temp.data = val;
      temp.next = head;
      head = temp;

      if (size == 0) {
        tail = temp;
      }

      size++;
    }

    public void addAt(int idx, int val) {
      if (idx < 0 || idx > size) {
        System.out.println("Invalid arguments");
      } else if (idx == 0) {
        addFirst(val);
      } else if (idx == size) {
        addLast(val);
      } else {
        Node node = new Node();
        node.data = val;

        Node temp = head;
        for (int i = 0; i < idx - 1; i++) {
          temp = temp.next;
        }
        node.next = temp.next;

        temp.next = node;
        size++;
      }
    }

    public void removeLast() {
      if (size == 0) {
        System.out.println("List is empty");
      } else if (size == 1) {
        head = tail = null;
        size = 0;
      } else {
        Node temp = head;
        for (int i = 0; i < size - 2; i++) {
          temp = temp.next;
        }

        tail = temp;
        tail.next = null;
        size--;
      }
    }

    public void removeAt(int idx) {
      if (idx < 0 || idx >= size) {
        System.out.println("Invalid arguments");
      } else if (idx == 0) {
        removeFirst();
      } else if (idx == size - 1) {
        removeLast();
      } else {
        Node temp = head;
        for (int i = 0; i < idx - 1; i++) {
          temp = temp.next;
        }

        temp.next = temp.next.next;
        size--;
      }
    }

    private Node getNodeAt(int idx) {
      Node temp = head;
      for (int i = 0; i < idx; i++) {
        temp = temp.next;
      }
      return temp;
    }

    public void reverseDI() {
      int li = 0;
      int ri = size - 1;
      while (li < ri) {
        Node left = getNodeAt(li);
        Node right = getNodeAt(ri);

        int temp = left.data;
        left.data = right.data;
        right.data = temp;

        li++;
        ri--;
      }
    }

    public void reversePI() {
      if (size <= 1) {
        return;
      }

      Node prev = null;
      Node curr = head;
      while (curr != null) {
        Node next = curr.next;

        curr.next = prev;
        prev = curr;
        curr = next;
      }

      Node temp = head;
      head = tail;
      tail = temp;
    }

    // K From Last
    // Move fast node k from starting (offset) and then use traverse till the end,
    // where slow ptr a the end is the ans
    public int kthFromLast(int k) {
      Node slow = head;
      Node fast = head;
      for (int i = 0; i < k; i++) {
        fast = fast.next;
      }

      while (fast != tail) {
        slow = slow.next;
        fast = fast.next;
      }

      return slow.data;
    }

    // Mid of linked list
    // 2 Ptr approach, slow is moved one node while fast is moved 2 nodes at a time.
    // Slow is ans when fast reaches end
    public int mid() {
      Node slow = head;
      Node fast = head;

      while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }

      return slow.data;
    }

    // Merge Sort 2 LL
    public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
      LinkedList list = new LinkedList();

      Node p1 = l1.head;
      Node p2 = l2.head;

      while (p1 != null && p2 != null) {
        if (p1.data < p2.data) {
          list.addLast(p1.data);
          p1 = p1.next;
        } else {
          list.addLast(p2.data);
          p2 = p2.next;
        }
      }

      while (p1 != null) {
        list.addLast(p1.data);
        p1 = p1.next;
      }

      while (p2 != null) {
        list.addLast(p2.data);
        p2 = p2.next;
      }

      return list;
    }

    // Merge Sort
    public static Node midNode(Node head, Node tail) {
      Node f = head;
      Node s = head;

      while (f.next != tail && f.next.next != tail) {
        f = f.next.next;
        s = s.next;
      }

      return s;
    }

    public static LinkedList mergeSort(Node head, Node tail) {
      if (head == tail) {
        LinkedList list = new LinkedList();
        list.addLast(head.data);
        return list;
      }

      Node mid = midNode(head, tail);
      LinkedList left = mergeSort(head, mid);
      LinkedList right = mergeSort(mid.next, tail);

      return mergeTwoSortedLists(left, right);
    }

    // Removing Duplicates
    public void removeDuplicates() {
      if (this.size() < 2)
        return;

      Node prev = head;
      Node curr = prev.next;

      while (curr != null) {
        while (curr != null && curr.data == prev.data) {
          curr = curr.next;
        }

        prev.next = curr;
        prev = prev.next;
        if (curr != null)
          curr = curr.next;
      }
    }

    // Odd Even List
    public void oddEven() {
      LinkedList odd = new LinkedList();
      LinkedList even = new LinkedList();

      while (head != null) {
        if (head.data % 2 == 0) {
          even.addLast(head.data);
        } else {
          odd.addLast(head.data);
        }
        removeFirst();
      }

      if (odd.size() > 0) {
        this.head = odd.head;
        this.tail = odd.tail;
        this.size = odd.size;

        if (even.size() > 0) {
          odd.tail.next = even.head;
          this.tail = even.tail;
          this.size = odd.size + even.size;
        }
      } else {
        this.head = even.head;
        this.tail = even.tail;
        this.size = even.size;
      }
    }

    // K Reverse
    public void kReverse(int k) {
      LinkedList list = new LinkedList();

      while (this.size() > 0) {
        if (this.size() >= k) {
          LinkedList tempList = new LinkedList();

          for (int i = 0; i < k; i++) {
            tempList.addFirst(this.getFirst());
            this.removeFirst();
          }

          if (list.size() == 0) {
            list = tempList;
          } else {
            list.tail.next = tempList.head;
            list.tail = tempList.tail;
          }
        } else {
          list.addLast(head.data);
          removeFirst();
        }
      }

      this.head = list.head;
      this.tail = list.tail;
      this.size = list.size;
    }

    // Palindrome
    Node left;

    public boolean IsPalindrome() {
      left = head;
      return isPalindromeHelper(left);
    }

    public boolean isPalindromeHelper(Node node) {
      if (node == null)
        return true;

      boolean result = isPalindromeHelper(node.next);

      if (result == false)
        return false;
      else if (left.data != node.data)
        return false;
      else {
        left = left.next;
        return true;
      }
    }

    // Fold
    public void fold() {
      left = head;
      foldHelper(left, 0);
    }

    public void foldHelper(Node node, int floor) {
      if (node == null)
        return;

      foldHelper(node.next, floor + 1);

      if (floor > size / 2) {
        Node temp = left.next;
        node.next = left.next;
        left.next = node;
        left = temp;
      } else if (floor == size / 2) {
        tail = node;
        node.next = null;
      }
    }

    // Addition of LL
    static LinkedList ans;
    public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
      ans = new LinkedList();

      int carry = addListHelper(one.head, one.size(), two.head, two.size());

      if (carry != 0) {
        ans.addFirst(carry);
      }

      return ans;
    }

    public static int addListHelper(Node one, int pv1, Node two, int pv2) {
      if (one == null && two == null) {
        return 0;
      }

      if (pv1 > pv2) {
        int carry = addListHelper(one.next, pv1 - 1, two, pv2);

        int sum = one.data + carry;
        int nd = sum % 10;
        int nc = sum / 10;

        ans.addFirst(nd);
        return nc;
      } else if (pv1 < pv2) {
        int carry = addListHelper(one, pv1, two.next, pv2 - 1);

        int sum = two.data + carry;
        int nd = sum % 10;
        int nc = sum / 10;

        ans.addFirst(nd);
        return nc;
      } else {
        int carry = addListHelper(one.next, pv1 - 1, two.next, pv2 - 1);

        int sum = one.data + two.data + carry;
        int nd = sum % 10;
        int nc = sum / 10;

        ans.addFirst(nd);
        return nc;
      }
    }

    // Intersection Node
    public static int findIntersection(LinkedList one, LinkedList two) {
      int s1 = one.size();
      int s2 = two.size();

      if (s1 > s2)
        return findIntersectionHelper(one, two);
      else
        return findIntersectionHelper(two, one);
    }

    public static int findIntersectionHelper(LinkedList large, LinkedList small) {
      int diff = large.size() - small.size();

      Node one = large.head;
      Node two = small.head;
      for (int i = 0; i < diff; i++) {
        one = one.next;
      }

      while (one != null && two != null && one.data != two.data) {
        one = one.next;
        two = two.next;
      }

      return one.data;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    LinkedList list = new LinkedList();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("addLast")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        list.addLast(val);
      } else if (str.startsWith("size")) {
        System.out.println(list.size());
      } else if (str.startsWith("display")) {
        list.display();
      } else if (str.startsWith("removeFirst")) {
        list.removeFirst();
      } else if (str.startsWith("getFirst")) {
        int val = list.getFirst();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("getLast")) {
        int val = list.getLast();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("getAt")) {
        int idx = Integer.parseInt(str.split(" ")[1]);
        int val = list.getAt(idx);
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("addFirst")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        list.addFirst(val);
      } else if (str.startsWith("addAt")) {
        int idx = Integer.parseInt(str.split(" ")[1]);
        int val = Integer.parseInt(str.split(" ")[2]);
        list.addAt(idx, val);
      } else if (str.startsWith("removeLast")) {
        list.removeLast();
      } else if (str.startsWith("removeAt")) {
        int idx = Integer.parseInt(str.split(" ")[1]);
        list.removeAt(idx);
      } else if (str.startsWith("reverseDI")) {
        list.reverseDI();
      } else if (str.startsWith("reversePI")) {
        list.reversePI();
      } else if (str.startsWith("kthFromEnd")) {
        int idx = Integer.parseInt(str.split(" ")[1]);
        System.out.println(list.kthFromLast(idx));
      }
      str = br.readLine();
    }
  }
}