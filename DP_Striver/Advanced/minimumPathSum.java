package Advanced;

// Q: - https://leetcode.com/problems/minimum-path-sum/
// Q: - https://www.geeksforgeeks.org/min-cost-path-dp-6/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-end-matrix-without-blocking/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-last-cell-last-column/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-destination-right-bottom-cell-matrix/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-destination-matrix-bottom-right-cell/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-destination-matrix-bottom-right-cell-set-2/
// Q: - https://www.geeksforgeeks.org/minimum-cost-reach-destination-matrix-bottom-right-cell-allowed-moves/

import java.util.Arrays;

public class minimumPathSum {
    public static void main(String[] args) {
        int[][] grid = { 
                        { 1, 8, 1 },
                        { 5, 5, 7 }, 
                        { 4, 8, 1 } 
                    }; // 7
        System.out.println(minPathSumRecursive(grid, grid.length-1, grid[0].length-1));
        int[][] dp = new int[grid.length][grid[0].length];
        for(int[] d : dp) Arrays.fill(d, -1);
        System.out.println(memo(grid, grid.length-1, grid[0].length-1, dp));
        System.out.println(tabulation(grid));
        System.out.println(tabulationSpaceOptimized(grid));
    }   

    // Recursive
    public static int minPathSumRecursive(int[][] grid, int m, int n) {
        if(m == 0 && n == 0) return grid[m][n];
        // if (m < 0 || n < 0) return Integer.MAX_VALUE;
        if (m == 0) return grid[m][n] + minPathSumRecursive(grid, m, n-1);
        if (n == 0) return grid[m][n] + minPathSumRecursive(grid, m-1, n);

        int up = grid[m][n] + minPathSumRecursive(grid, m-1, n);
        int left = grid[m][n] + minPathSumRecursive(grid, m, n-1);
        return Math.min(up, left);
    }
 
    // Memoization
    private static int memo(int[][] grid, int r, int c, int[][] dp) {
        if (r == 0 && c == 0) return grid[r][c];
        if (dp[r][c] != -1) return dp[r][c];
        if (r == 0) return grid[r][c] + memo(grid, r, c-1, dp);
        if (c == 0) return grid[r][c] + memo(grid, r-1, c, dp);
        dp[r][c] = grid[r][c] + Math.min(memo(grid, r - 1, c, dp), memo(grid, r, c - 1, dp));
        return dp[r][c];
    }

    // Tabulation
    public static int tabulation(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0]; // base case
        for (int i = 1; i < grid.length; i++) dp[i][0] = dp[i - 1][0] + grid[i][0]; // base case
        for (int i = 1; i < grid[0].length; i++) dp[0][i] = dp[0][i - 1] + grid[0][i]; // base case
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                int left = grid[i][j] + dp[i][j - 1];
                int up = grid[i][j] + dp[i-1][j];
                dp[i][j] = Math.min(left, up);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    // space optimized
    public static int tabulationSpaceOptimized(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0]; // base case
        for (int i = 1; i < grid[0].length; i++) dp[i] = dp[i - 1] + grid[0][i]; // base case
        for (int i = 1; i < grid.length; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                int left = grid[i][j] + dp[j - 1];
                int up = grid[i][j] + dp[j];
                dp[j] = Math.min(left, up);
            }
        }
        return dp[grid[0].length - 1];
    }
}
