import java.io.*;

public class MazePathsWithJumpPrint {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int m = Integer.valueOf(br.readLine());
        printMazePaths(1, 1, n, m, "");
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }
        
        for (int jump = 1; jump <= dc - sc; jump++) {
            printMazePaths(sr, sc + jump, dr, dc, psf + "h" + jump);
        }
        
        for (int jump = 1; jump <= dr - sr; jump++) {
            printMazePaths(sr + jump, sc, dr, dc, psf + "v" + jump);
        }
        
        for (int jump = 1; jump <= Math.min(dc - sc, dr - sr); jump++) {
            printMazePaths(sr + jump, sc + jump, dr, dc, psf + "d" + jump);
        }

    }
}
