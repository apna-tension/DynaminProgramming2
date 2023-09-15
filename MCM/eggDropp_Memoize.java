public class eggDropp_Memoize {
    public static void main(String[] args) {
        int e = 3;
        int f = 5;

        int[][] dp = new int[e+1][f+1];

        // initialize the dp array with -1
        for (int i = 0; i < e+1; i++) {
            for (int j = 0; j < f+1; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(memoize(e, f, dp));
    }

    private static int memoize(int e, int f, int[][] dp) {
        // base condition
        if (f <= 1 || e == 1) return f;

        // check if previously claculated or not
        if (dp[e][f] != -1) return dp[e][f];

        // initialize the min value 
        int min = Integer.MAX_VALUE;

        // check for all the floors from 1 to f and find the minimum attempts needed to find the minimum value for the worst case
        for (int k = 1; k <= f; k++) {

            // if the egg breaks then we need to check for the floors below it and the number of eggs left to check for the minimum value for the worst case
            int tempMin = 1 + Math.max(memoize(e-1, k-1, dp), memoize(e, f-k, dp));

            // take the minimum value for the worst case
            min = Math.min(min, tempMin);
        }

        // store the calculated result for next time use
        dp[e][f] = min;

        // return the minimum value for the worst case
        return dp[e][f];
    }
}
