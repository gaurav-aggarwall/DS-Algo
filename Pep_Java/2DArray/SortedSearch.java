import java.util.*;

public class SortedSearch {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        int[][] arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int ques = sc.nextInt();
        
        int i = 0;
        int j = n - 1;
        
        while(j >= 0 && i < n) {
            if (arr[i][j] == ques) {
                System.out.println(i);
                System.out.println(j);
                return;
            } else if (arr[i][j] > ques) {
                j--;
            } else {
                i++;
            }
        }
        
        System.out.println("Not Found");
    }

}