package LCS; // comment this line while execute in the local machine and uncomment the above lines of code and vice-versa

// Q:- Given a string, find length of the longest repeating subseequence such that the two subsequence don’t have same string character at same position, i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string. 
public class L_Repeating_Subsequence {
    public static void main(String[] args) {
        String s1 = "AABEBCDD";
        String s2 = s1;

        int n = s1.length();
        int m = s2.length();
        System.out.println("The length of the LRS is : " + tabulation(s1, s2, n, m));
    }

    private static int tabulation(String s1, String s2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        // calculate the lcs of the two string s1 and s2
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (i != j && s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // print the longest Repeating Subsequence
        String ans = "";
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1) && i != j) {
                ans += s1.charAt(i - 1);
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println("The longest repeating subsequence is : " + new StringBuilder(ans).reverse().toString());

        return dp[n][m];
    }
}
