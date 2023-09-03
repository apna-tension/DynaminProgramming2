package LCS; // before run this java file plese comment the package line cause when you run this java file it show that the main function does not found

public class print_LCS {
    public static void main(String[] args) {
        String s1 = "abcdgh";
        String s2 = "abedfhr";
        int n = s1.length();
        int m = s2.length();
        System.out.println("The length of the LCS is : " + tabulation(s1, s2, n, m));
    }
    // write the tabulation method with dp table
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
        // return max;
        // now we have to print the LCS
        int i = n;
        int j = m;
        String s = "";
        while (i > 0 && j > 0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                s = s1.charAt(i-1) + s;
                i--;
                j--;
            }
            else {
                if (dp[i][j-1] > dp[i-1][j]) {
                    j--;
                }
                else {
                    i--;
                }
            }
        }
        System.out.println("The Longest Common Subsequence is : " + s);
        return dp[n][m];
    }

}
