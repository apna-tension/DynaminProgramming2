import java.util.Arrays;
// Q:- Matrix Chain Multiplication using Memoization
public class memoizeMCM {
    public static void main(String[] args) {
        int[] arr = { 40, 20, 30, 10, 30 };
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println(solve(arr, 1, n - 1, dp));
    }

    // write the memoize code
    private static int solve(int[] arr, int i, int j, int[][] dp) {
        if (i >= j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int tempMinCost = 
                    solve(arr, i, k, dp)
                    + solve(arr, k + 1, j, dp)
                    + arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, tempMinCost);
        }
        dp[i][j] = minCost;
        return dp[i][j];
    }
}
