import java.io.*;
import java.util.*;

public class KeypadCode {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(getKPC(str));
    }

    static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"}; 

    public static ArrayList < String > getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> baseCaseList = new ArrayList<>();
            baseCaseList.add("");
            return baseCaseList;
        } 
        
        char startingChar = str.charAt(0);
        String nextRec = str.substring(1);
        String code = codes[startingChar - '0'];
        
        ArrayList<String> list = getKPC(nextRec);
        ArrayList<String> ans = new ArrayList<>();
        
        for(int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            
            for (String val : list) {
                ans.add(ch + val);
            }
        }
        return ans;   
    }

}