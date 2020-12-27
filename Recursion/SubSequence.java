import java.io.*;
import java.util.*;

public class SubSequence {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(gss(str));
    }

    public static ArrayList < String > gss(String str) {
        if (str.length() == 0) {
            ArrayList<String> baseCaseList = new ArrayList<>();
            baseCaseList.add("");
            return baseCaseList;
        } 
        
        char startingChar = str.charAt(0);
        String nextRec = str.substring(1);
        
        ArrayList<String> list = gss(nextRec);
        ArrayList<String> ans = new ArrayList<>();
        
        for (String val : list) {
            ans.add("" + val);
        }
        
        for (String val : list) {
            ans.add(startingChar + val);
        }
        
        return ans;    
    }
}