import java.io.*;
import java.util.*;

public class MazePathsWithJump {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        System.out.println(getMazePaths(0, 0, m-1, n-1));
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList < String > getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        } else if (sr > dr || sc > dc) {
            return new ArrayList<>();
        }
        
        ArrayList<String> ans = new ArrayList<>();
        
        for (int i = 1; i <= dc - sc; i++) {
            ArrayList<String> hStep = getMazePaths(sr, sc + i, dr, dc);
            
            for (String val : hStep) {
                ans.add("h" + i + val);
            }
        }
        
        for (int i = 1; i <= dr - sr; i++) {
            ArrayList<String> vStep = getMazePaths(sr + i, sc, dr, dc);
            
            for (String val : vStep) {
                ans.add("v" + i + val);
            }
        }

        for (int i = 1; i <= Math.min(dr - sr, dc - sc); i++) {
            ArrayList<String> dStep = getMazePaths(sr + i, sc + i, dr, dc);
            
            for (String val : dStep) {
                ans.add("d" + i + val);
            }
        }

        return ans;
    }

}