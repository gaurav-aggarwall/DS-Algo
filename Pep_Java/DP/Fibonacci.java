import java.util.*;

public class Fibonacci {

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    int[] arr = new int[n+1];
    
    for(int i = 0; i < arr.length; i++) {
        if(i == 0 || i == 1) {
            arr[i] = i;
        } else {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
    }
    
    System.out.println(arr[n]);

    sc.close();
 }

}