package LCS; // comment out this line before you executing your code in the local machine and vice-versa and uncomment the above lines of code

// Q: - Given two strings s1 and s2, find if s1 is a subsequence of s2 or not.
public class sequence_Pattern_Matching {
    public static void main(String[] args) {
        String s1 = "AXY";
        String s2 = "ADXCPYL";
        int n = s1.length();
        int m = s2.length();
        System.out.println(tabulation(s1, s2, n, m) == n ? "Yes s1 is a subsequence of s2" : "s1 is not a subsequence of s2");
    }

    private static int tabulation(String s1, String s2, int n, int m) {
        int dp[][] = new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        // calculate the lcs of the two string s1 and s2
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {

                // if the char at i-1 and j-1 are equal then we will add 1 to the previous diagonal element
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];

                // else we will take the max of the previous row and column
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
}

