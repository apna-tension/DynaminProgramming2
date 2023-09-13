public class evaluateExp_True_recursive {
    public static void main(String[] args) {
        String s = "T|T&F^t"; // in the string "T|T&F^T" we need to evaluate the expression and return the no of ways to evaluate the expression to true
        int i = 0, j = s.length() - 1;
        System.out.println(solve(s, i, j, true));

    }
    // write the solve method accoridng to the above main method
    private static int solve(String s, int i, int j, boolean isTrue) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue) return (s.charAt(i) == 'T' || s.charAt(i) == 't') ? 1 : 0;
            else return (s.charAt(i) == 'F' || s.charAt(i) == 'f') ? 1 : 0;
        }
        int ans = 0;
        for (int k = i + 1; k < j; k += 2) {
            int lt = solve(s, i, k - 1, true);
            int lf = solve(s, i, k - 1, false);
            int rt = solve(s, k + 1, j, true);
            int rf = solve(s, k + 1, j, false);
            if (s.charAt(k) == '&') {
                if (isTrue) ans += lt * rt;
                else ans += lt * rf + lf * rt + lf * rf;
            } else if (s.charAt(k) == '|') {
                if (isTrue) ans += lt * rt + lt * rf + lf * rt;
                else ans += lf * rf;
            } else if (s.charAt(k) == '^') {
                if (isTrue) ans += lt * rf + lf * rt;
                else ans += lt * rt + lf * rf;
            }
        }
        return ans;
    }
}