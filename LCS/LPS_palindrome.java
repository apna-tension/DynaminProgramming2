package LCS; //before run this java file plese comment the package line cause when you run this java file it show that the main function does not found


// Q: find the longest palindromic subsequence(LPS) and return the length of the LPS
public class LPS_palindrome {
    public static void main(String[] args) {
        String s1 = "greaebg";
        // s1.reverse();
        // now store the revese string of s1 in s2;
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();
        int m = s2.length();
        System.out.println("The length of the longest Palindromic Subsequence is : " + tabulation(s1, s2, n, m));
    }

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
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];

        // return 0;

    }
}
