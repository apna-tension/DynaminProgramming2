import java.util.Arrays;

public class palinPartiMemoOptimal {
    public static void main(String[] args) {
        String s = "nitinrbr"; // in the string "nitinrbr" there are two palindromic string 1. "nitin" and 2. "rbr" so we need to partition only one time so return the answer is 1
        int i = 0, j = s.length() - 1;
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Minimum number of partitions required: " + solve(s, i, j, dp));
    }

    private static int solve(String s, int i, int j, int[][] dp) {
        if (i >= j || isPalindrome(s, i, j)) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int minimumCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int left = dp[i][k] != -1 ? dp[i][k] : solve(s, i, k, dp);
            int right = dp[k+1][j] != -1 ? dp[k+1][j] : solve(s, k+1, j, dp);
            int tempMinCost = left + right + 1;
            minimumCost = Math.min(minimumCost, tempMinCost);
        }
        dp[i][j] = minimumCost;
        return dp[i][j];
    }

    // check wheather a string is palindrome or not
    private static boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return isPalindrome(s, i + 1, j - 1);
    }
}
