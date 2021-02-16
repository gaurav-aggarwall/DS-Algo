import java.io.*;
import java.util.*;

public class MazePaths {
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
        
        ArrayList<String> hStep = getMazePaths(sr, sc + 1, dr, dc);
        ArrayList<String> vStep = getMazePaths(sr + 1, sc, dr, dc);
        
        ArrayList<String> ans = new ArrayList<>();
        
        for (String val:hStep) {
            ans.add("h" + val);
        }
        for (String val:vStep) {
            ans.add("v" + val);
        }
    
        return ans;
    }
}