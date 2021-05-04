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

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
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