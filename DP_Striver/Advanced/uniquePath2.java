package Advanced;
// Q: - https://leetcode.com/problems/unique-paths-ii/

// import java.util.Scanner;

public class uniquePath2 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int row = sc.nextInt();
        // int col = sc.nextInt();
        int row = 4;
        int col = 4;
        int[][] grid = {
            {0,0,0,-1},
            {0,0,0,0},
            {0,0,-1,0},
            {0,0,0,0}
        };
        System.out.println("Recursion : " + recursion(row-1, col-1, grid));

        int[][] dp = new int[row][col];
        for (int[] arr : dp) {
            java.util.Arrays.fill(arr, -1);
        }
        System.out.println("Memoization : " + memoization(row-1, col-1, grid, dp));
        System.out.println("Tabulation : " + tabulation(row, col, grid));
        System.out.println("Optimal Space : " + optimalSpace(row, col, grid));
    }

    private static int recursion(int r, int c, int[][] grid) {
        if (r >= 0 && c >= 0 && grid[r][c] == -1) return 0;
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        int top = recursion(r-1, c, grid);
        int left = recursion(r, c-1, grid);
        return top+left;
    }


    private static int memoization(int r, int c, int[][] grid, int[][] dp) {
        if (r >= 0 && c >= 0 && grid[r][c] == -1) return 0;
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        if (dp[r][c] != -1) return dp[r][c];
        int top = memoization(r-1, c, grid, dp);
        int left = memoization(r, c-1, grid, dp);
        dp[r][c] = top + left;
        return dp[r][c];
    }

    private static int tabulation(int r, int c, int[][] grid) {
        // if (r >= 0 && c >= 0 && grid[r][c] == -1) return 0;
        // if (r == 0 && c == 0) return 1;
        // if (r < 0 || c < 0) return 0;
        // if (dp[r][c] != -1) return dp[r][c];
        // int top = memoization(r-1, c, grid, dp);
        // int left = memoization(r, c-1, grid, dp);
        // dp[r][c] = top + left;
        // return dp[r][c];
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            if (grid[i][0] == -1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < c; i++) {
            if (grid[0][i] == -1) break;
            dp[0][i] = 1;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (grid[i][j] == -1) continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[r-1][c-1];
    }

    // space optimization
    private static int optimalSpace(int r, int c, int[][] grid) {
        int[] dp = new int[c];
        dp[0] = 1;
        for (int i = 0; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (grid[i][j] == -1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] += dp[j-1];
            }
        }
        return dp[c-1];
    }
}
