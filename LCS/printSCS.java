package LCS;

public class printSCS {
    public static void main(String[] args) {
        String s1 = "acbcf";
        String s2 = "abcdaf";

        int n = s1.length();
        int m = s2.length();
        System.out.println("The length of the SCS is : " + tabulation(s1, s2, n, m));
    }

    // tabulation method for SCS
    private static int tabulation(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];

        // Initialize the 0'th row and column of the dp table 
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

        String ans = "";
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                ans += s1.charAt(i-1);
                i--;
                j--;
            } else {
                if (dp[i][j-1] > dp[i-1][j]) {
                    ans += s2.charAt(j-1);
                    j--;
                } else {
                    ans += s1.charAt(i-1);
                    i--;
                }
            }
        }
        while (i > 0) {
            ans += s1.charAt(i-1);
            i--;
        }

        while (j > 0) {
            ans += s2.charAt(j-1);
            j--;
        }
        System.out.println("The shortest common supersequence is : " + new StringBuilder(ans).reverse().toString());
        return dp[n][m];
    }
}
