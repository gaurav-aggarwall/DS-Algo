import java.io.*;
import java.util.*;

public class PrintPermutations {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        printPermutations(s, "");
    }

    public static void printPermutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            String left = str.substring(0, i);
            String right = str.substring(i + 1);
    
            printPermutations(left + right, ans + ch);
        }
    }

}