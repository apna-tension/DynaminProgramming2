package Advanced;
// Claculate all the possible path from top left to bottom right in a grid

// Q: - https://leetcode.com/problems/unique-paths/
// import java.io.BufferedReader;
import java.util.*;
public class allPossiblePathOfGrid {
    public static void main(String[] args) {
        // DataInputStream dis = new DataInputStream(System.in);
        // System.out.println("Enter the row and column : ");
        // int row = 0;
        // int col = 0;
        // try {
        //     row = Integer.parseInt(dis.readLine());
        //     col = Integer.parseInt(dis.readLine());
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row and column : ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.close();

        // Recursion
        System.out.println("Recursion : " + recursion(row-1, col-1));

        // Memoization
        int[][] dp = new int[row][col];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        System.out.println("Memoization : " + memoization(row-1, col-1, dp));


        // Tabulation
        System.out.println("Tabulation Table : - ");
        System.out.println("Tabulation : " + tabulation(row, col));
        System.out.println("Optimal Space : " + optimalSpace(row, col));
    }

    // Recursion
    private static int recursion(int r, int c) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        int top = recursion(r-1, c);
        int left = recursion(r, c-1);
        return top + left;
    }

    // Memoization  
    private static int memoization(int r, int c, int[][] dp) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        if (dp[r][c] != -1) return dp[r][c];
        int left = memoization(r, c-1, dp);
        int right = memoization(r-1, c, dp);
        dp[r][c] = left + right;
        return dp[r][c];

    }

    // Tabulation
    private static int tabulation(int r, int c) {
        int[][] dp = new int[r][c];

        // fill the first row and column with 1
        for (int i = 0; i < r; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < c; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[r-1][c-1];
    }

    // Optimal Space
    private static int optimalSpace(int r, int c) {
        // initialize the dp array with 1
        int[] dp = new int[c];
        System.out.println("Optimal dp table");
        Arrays.fill(dp, 1);

        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                // calculate the sum of top and left element and store it in the dp array for later use in the next iteration 
                dp[j] = dp[j] + dp[j-1];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[c-1];
    }

}
