import java.util.Arrays;

// Q: - - - - - - -: given a string and you have to find the minimum number of partitions required to make the string palindrome
public class palindromePartitinon_Memoize {
    public static void main(String[] args) {
        String s = "nitin";
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
            int tempMinCost = 
                    solve(s, i, k, dp)
                    + solve(s, k + 1, j, dp)
                    + 1;
            minimumCost = Math.min(minimumCost, tempMinCost);
        }
        dp[i][j] = minimumCost;
        return dp[i][j];
    }

    private static boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return isPalindrome(s, i + 1, j - 1);
    }
}
