import java.util.Arrays;
// Q: Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.  
// - A scrambled string is a string that is formed by taking a substring of s1 and appending it at any position in s1.
// - For example, "abcde" is a scrambled string of "aebcd" because you can take substring "ae" and append it at position 1 to get "aebcd".
// - We can split the string into two parts and then swap the two parts and check if the two strings are equal or not

public class scramble_Memoize_Optimize {
    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat"; // In this string s2 if we split it into two parts - "rg" and "eat" then we can
                             // swap the "rg" to "gr" and get the string s1
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        // initialize the dp array with false
        for (boolean[] row : dp) {
            Arrays.fill(row, false);
        }

        System.out.println(solve(s1, s2, dp));
    }

    private static boolean solve(String s1, String s2, boolean[][] dp) {

        // base case - if the two strings are not of equal length then return false
        if (s1.length() != s2.length())
            return false;

        // base case - if the two strings are equal then return true
        if (s1.equals(s2))
            return true;

        // base case - if the two strings are of length 1 then return false
        if (s1.length() <= 1)
            return false;

        if (dp[s1.length()][s2.length()] != false)
            return dp[s1.length()][s2.length()];
        // if the two strings are not equal then we need to check if the two strings are
        // scrambled or not
        int n = s1.length();
        boolean flag = false;
        for (int i = 1; i < n; i++) {
            // if we swap the two parts of the string and check if the two strings are equal
            // or not
            // then we can say that the string is scrambled
            boolean cond1 = dp[i][n - i] != false ? dp[i][n - i]
                    : solve(s1.substring(0,
                            i), s2.substring(n - i, n), dp)
                            && dp[n - i][i] != false ? dp[n - i][i]
                                    : solve(s1.substring(i, n),
                                            s2.substring(0, n - i), dp);

            // if we don't swap the two parts of the string and check if the two strings are
            // equal or not
            // then also we can say that the string is scrambled
            boolean cond2 = dp[i][i] != false ? dp[i][i]
                    : solve(s1.substring(0, i), s2.substring(0, i), dp)
                            && dp[n - i][n - i] != false ? dp[n - i][n - i]
                                    : solve(s1.substring(i, n), s2.substring(i, n), dp);

            // if any of the above two conditions are true then we can say that the string
            // is scrambled
            if (cond1 || cond2) {
                flag = true;
                break;
            }
        }
        dp[s1.length()][s2.length()] = flag;
        return flag;
    }
}
