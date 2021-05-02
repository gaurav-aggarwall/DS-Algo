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

  // Merge K Sorted
  public static class Pair implements Comparable<Pair> {
    int value;
    int list;
    int index;

    Pair(int value, int list, int index) {
      this.value = value;
      this.list = list;
      this.index = index;
    }

    public int compareTo(Pair o) {
      return this.value - o.value;
    }
  }

  public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
    ArrayList<Integer> rv = new ArrayList<>();

    PriorityQueue<Pair> pq = new PriorityQueue<Pair>();

    for (int i = 0; i < lists.size(); i++) {
      pq.add(new Pair(lists.get(i).get(0), i, 0));
    }

    while (pq.size() > 0) {
      Pair pair = pq.remove();
      rv.add(pair.value);
      pair.index++;

      if (pair.index < lists.get(pair.list).size()) {
        pair.value = lists.get(pair.list).get(pair.index);
        pq.add(pair);
      }
    }

    return rv;
  }

  public static void main(String[] args) throws Exception {
    // Too lazy to handle inut and call functions :p
  }
}
