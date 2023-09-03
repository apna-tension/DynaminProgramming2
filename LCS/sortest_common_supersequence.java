package LCS;

public class sortest_common_supersequence {
    public static void main(String[] args) {
        String s1 = "abcdgh";
        String s2 = "abedfhr";
        int n = s1.length();
        int m = s2.length();
        // System.out.println("The length of the SCS is : " + tabulation(s1, s2, n, m));
        System.out.println("Sores common supersequences length is : " + (n + m - tabulation(s1, s2, n, m)));
    }

    private static int tabulation(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];
        
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[n][m];
    }
}
