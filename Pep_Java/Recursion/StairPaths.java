import java.io.*;
import java.util.*;

public class StairPaths {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(getStairPaths(n));
    }

    public static ArrayList < String > getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;
        } else if (n < 0) {
            return new ArrayList<>();
        }
        
        ArrayList<String> oneStep = getStairPaths(n-1);
        ArrayList<String> twoStep = getStairPaths(n-2);
        ArrayList<String> threeStep = getStairPaths(n-3);
        
        ArrayList<String> ans = new ArrayList<>();
        
        for (String val:oneStep) {
            ans.add("1" + val);
        }
        for (String val:twoStep) {
            ans.add("2" + val);
        }
        for (String val:threeStep) {
            ans.add("3" + val);
        }
    
        return ans;
    }

}