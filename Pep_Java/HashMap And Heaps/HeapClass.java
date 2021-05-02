import java.util.*;

public class HeapClass {

  // K Largest elements
  public static void kLargest(int[] arr, int n, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

    for (int val : arr) {
      if (pq.size() < k)
        pq.add(val);
      else {
        if (pq.peek() < val) {
          pq.remove();
          pq.add(val);
        }
      }
    }
    for (int i = 0; i < k; i++) {
      System.out.println(pq.remove());
    }
  }

  // K Sorted Array
  public static void kSorted(int[] arr, int n, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

    for (int i = 0; i < n; i++) {
      int val = arr[i];
      if (pq.size() < k)
        pq.add(val);
      else {
        if (val < pq.peek())
          System.out.println(val);
        else {
          System.out.println(pq.remove());
          pq.add(val);
        }
      }
    }
    for (int i = 0; i < k; i++) {
      System.out.println(pq.remove());
    }
  }

  public static void main(String[] args) throws Exception {
    // Too lazy to handle inut and call functions :p
  }
}
