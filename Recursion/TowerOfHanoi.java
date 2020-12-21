import java.io.*;

public class TowerOfHanoi {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        toh(n, a, b, c);
    }

    public static void toh(int n, int a, int b, int c){
        if (n == 0) return;
        
        toh(n-1, a, c, b);
        System.out.println(n + "[" + a + " -> " + b + "]");
        toh(n-1, c, b, a);
    }

}