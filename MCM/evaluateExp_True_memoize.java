// Q: Q: we are given a string which contains T,F,|,&,^ and we need to evaluate the expression and return the no of ways to evaluate the expression to true

import java.util.Arrays;
public class evaluateExp_True_memoize {
    public static void main(String[] args) {
        String s = "T|T&F^t"; // in the string "T|T&F^T" we need to evaluate the expression and return the no of ways to evaluate the expression to true
        int i = 0, j = s.length() - 1;
        int[][][] dp = new int[s.length() + 1][s.length() + 1][2];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
        System.out.println(solve(s, i, j, true, dp));

    }

    // write the solve method accoridng to the above main method
    private static int solve(String s, int i, int j, boolean isTrue, int[][][] dp) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue) return (s.charAt(i) == 'T' || s.charAt(i) == 't') ? 1 : 0;
            else return (s.charAt(i) == 'F' || s.charAt(i) == 'f') ? 1 : 0;
        }
        if (dp[i][j][isTrue ? 1 : 0] != -1) return dp[i][j][isTrue ? 1 : 0];
        int ans = 0;
        for (int k = i + 1; k < j; k += 2) {
            int lt = dp[i][k - 1][1] != -1 ? dp[i][k - 1][1] : solve(s, i, k - 1, true, dp);
            int lf = dp[i][k - 1][0] != -1 ? dp[i][k - 1][0] : solve(s, i, k - 1, false, dp);
            int rt = dp[k + 1][j][1] != -1 ? dp[k + 1][j][1] : solve(s, k + 1, j, true, dp);
            int rf = dp[k + 1][j][0] != -1 ? dp[k + 1][j][0] : solve(s, k + 1, j, false, dp);
            if (s.charAt(k) == '&') {
                if (isTrue) ans += lt * rt;
                else ans += lt * rf + lf * rt + lf * rf;
            } else if (s.charAt(k) == '|') {
                if (isTrue) ans += lt * rt + lt * rf + lf * rt;
                else ans += lf * rf;
            } else if (s.charAt(k) == '^') {
                if (isTrue) ans += lt * rf + lf * rt;
                else ans += lt * rt + lf * rf;
            }
        }
        dp[i][j][isTrue ? 1 : 0] = ans;
        return ans;
    }
}
