package LCS; // before executing this code in the local machine comment out this line and uncomment the above lines of code
// Q:- Given a string s1, we have to find the minimum number of character are insertions to make the string palindrome.
public class minNoOf_Insertion_makePalindromeStr {
    public static void main(String[] args) {
        String s1 = "agbcbea";
        // to make the string palindrome we have to find the lcs of the string and its reverse
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();
        int m = s2.length();
        System.out.println("The minimum number of insertion to make the string palindrome is: " + (n - tabulation(s1, s2, n, m)));
    }

    private static int tabulation(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];

        // Initialize the 0'th row and column with 0 cause if any of the string is empty or null then the lcs will be 0
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1; j++) {
                dp[i][j] = 0;
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

        // return the length of the lcs
        return dp[n][m];
    }
}
