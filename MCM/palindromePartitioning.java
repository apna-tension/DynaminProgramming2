public class palindromePartitioning {
    public static void main(String[] args) {
        String s = "nitin";
        int i = 0, j = s.length() - 1;
        System.out.println(solve(s, i, j));
    }

    private static int solve(String str1, int i, int j) {
        if (i >= j) return 0;
        if (isPalindrome(str1, i, j)) return 0;
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int tempMinCost = 
                    solve(str1, i, k)
                    + solve(str1, k + 1, j)
                    + 1;
            minCost = Math.min(minCost, tempMinCost);
        }
        return minCost;
    }
    // write the isPlaindrome method
    private static boolean isPalindrome(String str1, int i, int j) {
        if (i >= j) return true;
        if (str1.charAt(i) != str1.charAt(j)) return false;
        return isPalindrome(str1, i + 1, j - 1);
    }
}