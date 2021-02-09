import java.io.*;

public class SubSequencePrint {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        printSS(str, "");
    }

    public static void printSS(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }    
        
        char ch = str.charAt(0);
        String rem = str.substring(1);
        
        printSS(rem, ans + ch);
        printSS(rem, ans + "");
    }

}