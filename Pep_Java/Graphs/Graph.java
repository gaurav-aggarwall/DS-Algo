import java.io.*;
import java.util.*;

public class Graph {
  static class Edge {
    int src;
    int nbr;
    int wt;

    Edge(int src, int nbr, int wt) {
      this.src = src;
      this.nbr = nbr;
      this.wt = wt;
    }
  }

  // Has Path
  public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
    if (src == dest)
      return true;

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (visited[edge.nbr] == false) {
        boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
        if (hasNbrPath == true)
          return true;
      }
    }

    return false;
  }

  // Print All Paths
  public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String path) {
    if (src == dest) {
      System.out.println(path + src);
      return;
    }

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (visited[edge.nbr] == false) {
        printAllPaths(graph, edge.nbr, dest, visited, path + src);
      }
    }
    visited[src] = false;
  }

  // Multisolver
  static class Pair implements Comparable<Pair> {
    int wsf;
    String psf;

    Pair(int wsf, String psf) {
      this.wsf = wsf;
      this.psf = psf;
    }

    public int compareTo(Pair o) {
      return this.wsf - o.wsf;
    }
  }

  static String spath;
  static Integer spathwt = Integer.MAX_VALUE;
  static String lpath;
  static Integer lpathwt = Integer.MIN_VALUE;
  static String cpath;
  static Integer cpathwt = Integer.MAX_VALUE;
  static String fpath;
  static Integer fpathwt = Integer.MIN_VALUE;
  static PriorityQueue<Pair> pq = new PriorityQueue<>();

  public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k,
      String psf, int wsf) {
    if (src == dest) {
      if (wsf < spathwt) {
        spath = psf;
        spathwt = wsf;
      }

      if (wsf > lpathwt) {
        lpath = psf;
        lpathwt = wsf;
      }

      if (wsf > criteria && wsf < cpathwt) {
        cpath = psf;
        cpathwt = wsf;
      }

      if (wsf < criteria && wsf > fpathwt) {
        fpath = psf;
        fpathwt = wsf;
      }

      if (pq.size() < k) {
        pq.add(new Pair(wsf, psf));
      } else {
        Pair pair = pq.remove();
        if (pair.wsf < wsf) {
          pair = new Pair(wsf, psf);
        }
        pq.add(pair);
      }

      return;
    }

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (visited[edge.nbr] == false) {
        multisolver(graph, edge.nbr, dest, visited, criteria, k, psf + edge.nbr, wsf + edge.wt);
      }
    }
    visited[src] = false;
  }

  // Get Connected Componets
  // Loop over all vertices and call drawTree and add the component to componets
  // In Draw Tree -> travel all nbr vertex of current src vertex and add them in a
  // component
  public static ArrayList<ArrayList<Integer>> getConnectedPaths(ArrayList<Edge>[] graph, boolean[] visited) {
    ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>();

    for (int vtc = 0; vtc < graph.length; vtc++) {
      if (visited[vtc] == false) {
        visited[vtc] = true;
        ArrayList<Integer> component = new ArrayList<Integer>();
        drawTree(graph, vtc, visited, component);
        components.add(component);
      }
    }

    return components;
  }

  public static void drawTree(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> comp) {
    visited[src] = true;
    comp.add(src);

    for (Edge edge : graph[src]) {
      if (visited[edge.nbr] == false) {
        drawTree(graph, edge.nbr, visited, comp);
      }
    }
  }

  // No of Islands
  // 0 - Land, 1 - Watet
  public static int getNumberOfIslands(int[][] arr, boolean[][] visited) {
    int islands = 0;

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == 0 && visited[i][j] == false) {
          visitIsland(arr, i, j, visited);
          islands++;
        }
      }
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(visited[i][j] + " ");
      }
      System.out.println();
    }

    return islands;
  }

  // Visit Island
  public static void visitIsland(int[][] arr, int i, int j, boolean[][] visited) {
    if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == 1 || visited[i][j] == true)
      return;

    visited[i][j] = true;

    visitIsland(arr, i - 1, j, visited);
    visitIsland(arr, i, j + 1, visited);
    visitIsland(arr, i + 1, j, visited);
    visitIsland(arr, i, j - 1, visited);
  }

  // Hamiltonaian Path / Cycle
  // If set has all nodes meaning we are on last node and it has original source
  // as neighnour thrn cylce otherwise path
  public static void hamiltonian(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited, String psf, int osrc) {
    if (visited.size() == graph.length - 1) {
      boolean cycle = false;
      for (Edge edge : graph[src]) {
        if (edge.nbr == osrc) {
          cycle = true;
          break;
        }
      }
      if (cycle) {
        psf += "*";
      } else {
        psf += ".";
      }
      System.out.println(psf);
      return;
    }

    visited.add(src);
    for (Edge edge : graph[src]) {
      if (visited.contains(edge.nbr) == false) {
        hamiltonian(graph, edge.nbr, visited, psf + edge.nbr, osrc);
      }
    }
    visited.remove(src);

  }

  // BFS
  static class BFSPair {
    int v;
    String psf;

    BFSPair(int v, String psf) {
      this.v = v;
      this.psf = psf;
    }
  }

  public static void bfs(ArrayList<Edge>[] graph, int src, boolean[] visited) {
    ArrayDeque<BFSPair> q = new ArrayDeque<BFSPair>();
    q.add(new BFSPair(src, src + ""));
    visited[src] = true;

    while (q.size() > 0) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        BFSPair pair = q.remove();
        System.out.println(pair.v + "@" + pair.psf);
        for (Edge edge : graph[pair.v]) {
          if (visited[edge.nbr] == false) {
            visited[edge.nbr] = true;
            q.add(new BFSPair(edge.nbr, pair.psf + edge.nbr));
          }
        }
      }
    }
  }

  // Is Cyclic
  public static void isCyclic(ArrayList<Edge>[] graph, boolean[] visited) {
    for (int v = 0; v < graph.length; v++) {
      if (visited[v] == false) {
        boolean cycle = isCyclicHelper(graph, v, visited);
        if (cycle) {
          System.out.println(true);
          return;
        }
      }
    }
    System.out.println(false);
  }

  public static boolean isCyclicHelper(ArrayList<Edge>[] graph, int src, boolean[] visited) {
    ArrayDeque<BFSPair> q = new ArrayDeque<BFSPair>();
    q.add(new BFSPair(src, src + ""));

    while (q.size() > 0) {
      BFSPair pair = q.remove();
      if (visited[pair.v] == true) {
        return true;
      }
      visited[pair.v] = true;
      for (Edge edge : graph[pair.v]) {
        if (visited[edge.nbr] == false) {
          q.add(new BFSPair(edge.nbr, pair.psf + edge.nbr));
        }
      }
    }
    return false;
  }

  // Is BiPartite
  // Visited is int array with -1 as prefilled value to mark it unvisited
  // Test for each component as every component must be bipartite
  // When BFS traversing if the vertex is visited already and its level is same as
  // in visited array then its ok
  // otherwise it is not bipartite as it has odd number of vertex
  public static void isBipartite(ArrayList<Edge>[] graph, int[] visited) {
    for (int v = 0; v < graph.length; v++) {
      if (visited[v] == -1) {
        boolean isCompBipartite = isBipartiteHelper(graph, v, visited);
        if (isCompBipartite == false) {
          System.out.println(false);
          return;
        }
      }
    }
    System.out.println(true);
  }

  public static class BPTPair {
    int v;
    int level;

    BPTPair(int v, int level) {
      this.v = v;
      this.level = level;
    }
  }

  public static boolean isBipartiteHelper(ArrayList<Edge>[] graph, int src, int[] visited) {
    ArrayDeque<BPTPair> q = new ArrayDeque<BPTPair>();
    q.add(new BPTPair(src, 0));

    while (q.size() > 0) {
      BPTPair pair = q.remove();

      if (visited[pair.v] != -1) {
        if (pair.level != visited[pair.v]) {
          return false;
        }
      } else {
        visited[pair.v] = pair.level;
      }

      for (Edge edge : graph[pair.v]) {
        if (visited[edge.nbr] == -1) {
          q.add(new BPTPair(edge.nbr, pair.level + 1));
        }
      }
    }

    return true;
  }

  // Spread of Infection
  public static class IPair {
    int v;
    int t;

    IPair(int v, int t) {
      this.v = v;
      this.t = t;
    }
  }

  public static int infection(ArrayList<Edge>[] graph, int src, int t, boolean[] visited) {
    int count = 0;

    ArrayDeque<IPair> q = new ArrayDeque<IPair>();
    q.add(new IPair(src, 1));

    while (q.size() > 0) {
      IPair pair = q.remove();

      if (visited[pair.v] == true)
        continue;

      if (pair.t <= t) {
        count++;
      } else {
        break;
      }

      visited[pair.v] = true;

      for (Edge edge : graph[pair.v]) {
        if (visited[edge.nbr] == false) {
          q.add(new IPair(edge.nbr, pair.t + 1));
        }
      }
    }

    return count;
  }

  // Shortest Path - Dijkstra
  // Same as BFS but use Priority Queue as we select at every stage the vertex
  // which is closest to current vertex
  static class SPPair {
    int v;
    String psf;
    int wsf;

    SPPair(int v, String psf, int wsf) {
      this.v = v;
      this.psf = psf;
      this.wsf = wsf;
    }
  }

  public static void shortestPath(ArrayList<Edge>[] graph, int src, boolean[] visited) {
    PriorityQueue<SPPair> q = new PriorityQueue<SPPair>((a, b) -> a.wsf - b.wsf);
    q.add(new SPPair(src, src + "", 0));

    while (q.size() > 0) {
      SPPair pair = q.remove();

      if (visited[pair.v] == true)
        continue;
      else
        visited[pair.v] = true;

      System.out.println(pair.v + " via " + pair.psf + " @ " + pair.wsf);

      for (Edge edge : graph[pair.v]) {
        if (visited[edge.nbr] == false) {
          q.add(new SPPair(edge.nbr, pair.psf + edge.nbr, pair.wsf + edge.wt));
        }
      }
    }
  }

  // Min Spanning Tree - Prim's Algo
  // Same as Dijksrta, but at each stage instead of psf edge's wt is considered
  static class PrimPair {
    int v;
    int av;
    int wt;

    PrimPair(int v, int av, int wt) {
      this.v = v;
      this.av = av;
      this.wt = wt;
    }
  }

  public static void spanningTree(ArrayList<Edge>[] graph, int src, boolean[] visited) {
    PriorityQueue<PrimPair> q = new PriorityQueue<PrimPair>((a, b) -> a.wt - b.wt);
    q.add(new PrimPair(src, -1, 0));

    while (q.size() > 0) {
      PrimPair pair = q.remove();

      if (visited[pair.v] == true)
        continue;
      else
        visited[pair.v] = true;

      if (pair.av != -1)
        System.out.println("[" + pair.v + "-" + pair.av + "@" + pair.wt + "]");

      for (Edge edge : graph[pair.v]) {
        if (visited[edge.nbr] == false) {
          q.add(new PrimPair(edge.nbr, pair.v, edge.wt));
        }
      }
    }
  }

  // Order of Compilation - Reverse Topological Order
  public static void topologicalSort(ArrayList<Edge>[] graph, boolean[] visited) {
    Stack<Integer> st = new Stack<Integer>();

    for (int v = 0; v < graph.length; v++) {
      if (visited[v] == false) {
        traversal(graph, v, st, visited);
      }
    }

    while (st.size() > 0) {
      System.out.println(st.pop());
    }
  }

  public static void traversal(ArrayList<Edge>[] graph, int src, Stack<Integer> st, boolean[] visited) {
    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (visited[edge.nbr] == false) {
        traversal(graph, edge.nbr, st, visited);
      }
    }
    st.push(src);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
    @SuppressWarnings("unchecked")
    ArrayList<Edge>[] graph = new ArrayList[vtces];
    for (int i = 0; i < vtces; i++) {
      graph[i] = new ArrayList<>();
    }

    int edges = Integer.parseInt(br.readLine());
    for (int i = 0; i < edges; i++) {
      String[] parts = br.readLine().split(" ");
      int v1 = Integer.parseInt(parts[0]);
      int v2 = Integer.parseInt(parts[1]);
      int wt = Integer.parseInt(parts[2]);
      graph[v1].add(new Edge(v1, v2, wt));
      graph[v2].add(new Edge(v2, v1, wt));
    }

    int src = Integer.parseInt(br.readLine());
    int dest = Integer.parseInt(br.readLine());

    boolean[] visited = new boolean[vtces];

    boolean path = hasPath(graph, src, dest, visited);

    System.out.println(path);
  }

}