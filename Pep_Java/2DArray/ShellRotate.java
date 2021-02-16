// Step 1: Extract shell elements in an array.
// Step 2: Rotate that aray by 'r' rotations.
//         For rotation, reverse first part (A'), reverse second part (B') and reverse whole (A'B')'
//         BA ->  (A'B')'
// Step 3: Refill the shell from this updated array

import java.util.*;

public class ShellRotate {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] arr = new int[n][m];
        
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        
        int s = sc.nextInt();
        int r = sc.nextInt();
        
        ringRotate(arr, s, r);
        display(arr);

        sc.close();
    }
    
    public static void ringRotate(int[][] arr, int s, int r) {
        int[] oda = getOneDArray(arr, s);
        rotate(oda, r);
        fillOneDArray(arr, s, oda);    
    }
    
    public static int[] getOneDArray(int[][] arr, int s) {
        int minr = s - 1;
        int minc = s - 1;
        
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
        
        int size = 2 * (maxr - minr + maxc - minc);
        
        int[] oda = new int[size];
        int index = 0;
        
        for(int i = minr, j = minc; i <= maxr; i++) {
            oda[index++] = arr[i][j];
        }
        
        for(int i = maxr, j = minc + 1; j <= maxc; j++) {
            oda[index++] = arr[i][j];
        }
        
        for(int i = maxr - 1, j = maxc; i >= minr; i--) {
            oda[index++] = arr[i][j];
        }
        
        for(int i = minr, j = maxc - 1; j >= minc + 1; j--) {
            oda[index++] = arr[i][j];
        }
        
        return oda;
    }
    
    public static void rotate(int[] arr, int r) {
        r = r % arr.length;
        
        if (r < 0) {
            r += arr.length;
        }
        
        reverse(arr, 0, arr.length - r - 1);
        reverse(arr, arr.length - r, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }
    
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            start++;
            end--;
        }
    }
    
    public static void fillOneDArray(int[][]arr, int s, int[] oda) {
        int minr = s - 1;
        int minc = s - 1;
        
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
        
        int index = 0;
        
        for(int i = minr, j = minc; i <= maxr; i++) {
            arr[i][j] = oda[index++];
        }
        
        for(int i = maxr, j = minc + 1; j <= maxc; j++) {
            arr[i][j] = oda[index++];
        }
        
        for(int i = maxr - 1, j = maxc; i >= minr; i--) {
            arr[i][j] = oda[index++];
        }
        
        for(int i = minr, j = maxc - 1; j >= minc + 1; j--) {
            arr[i][j] = oda[index++];
        }
    }
    
    public static void display(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}