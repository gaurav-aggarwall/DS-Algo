import java.util.*;

public class KnightsTour {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        
        int[][] arr = new int[n][n];
        
        int row = scn.nextInt();
        int col = scn.nextInt();
        
        printKnightsTour(arr, row, col, 1);

        scn.close();
    }

    public static void printKnightsTour(int[][] chess, int row, int col, int step) {
        if (row < 0 || col < 0 ||
            row >= chess.length || col >= chess.length ||
            chess[row][col] > 0
        ) {
            return;
        }
        
        if (step == chess.length * chess.length) {
            chess[row][col] = step;
            displayBoard(chess);
            chess[row][col] = 0;
            return;
        }
        
        chess[row][col] = step;
        
        printKnightsTour(chess, row - 2, col + 1, step + 1);
        printKnightsTour(chess, row - 1, col + 2, step + 1);
        printKnightsTour(chess, row + 1, col + 2, step + 1);
        printKnightsTour(chess, row + 2, col + 1, step + 1);
        printKnightsTour(chess, row + 2, col - 1, step + 1);
        printKnightsTour(chess, row + 1, col - 2, step + 1);
        printKnightsTour(chess, row - 1, col - 2, step + 1);
        printKnightsTour(chess, row - 2, col - 1, step + 1);
        
        chess[row][col] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}