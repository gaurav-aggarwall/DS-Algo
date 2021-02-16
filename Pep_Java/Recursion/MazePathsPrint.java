import java.io.*;

public class MazePathsPrint {
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
        
        if (sc < dc) {
            printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        }
        
        if (sr < dr) {
            printMazePaths(sr + 1, sc, dr, dc, psf + "v");
        }
        
    }

}