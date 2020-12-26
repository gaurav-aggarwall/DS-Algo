// Print all indices of a number in an array

import java.io.*;

public class AllIndices {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int x = Integer.parseInt(br.readLine());
        int[] iarr = allIndices(arr, x, 0, 0);

        if (iarr.length == 0) {
            System.out.println();
            return;
        }

        for (int i = 0; i < iarr.length; i++) {
            System.out.println(iarr[i]);
        }
    }

    public static int[] allIndices(int[] arr, int x, int idx, int fsf) {
        if (idx == arr.length) return new int[fsf];
        
        int fsfc = fsf; 
        if (arr[idx] == x) fsfc++;
        int[] arrLocal = allIndices(arr, x, idx + 1, fsfc);

        if (arr[idx] == x) {
            arrLocal[fsf] = idx;
        }
        return arrLocal;
    }

}