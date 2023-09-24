package Advanced;

// Q:(GfG) - https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
// Q:(Leetcode) - https://leetcode.com/problems/house-robber/
// Question: - Find the maximum sum of elements such that no two elements are adjacent

import java.util.Arrays;

public class maxSumNonAdj {
    public static void main(String[] args) {
        int[] arr = {7, 9, 10, 100, 111, 10, 5};
        System.out.println(recursive(arr, arr.length-1));
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        System.out.println(memoization(arr, arr.length-1, dp));
        System.out.println(tabulation(arr));
        System.out.println(optimalSpace(arr));

    }
    

    // Recursive approach
    private static int recursive(int[] arr, int n) {
        if (n == 0) return arr[0];
        if (n < 0) return 0;
        return Math.max(recursive(arr, n-1), recursive(arr, n-2) + arr[n]);
    }

    // Memoization approach
    private static int memoization(int[] arr, int n, int[] dp) {
        if (n == 0) return arr[0];
        if (n < 0) return 0;
        if (dp[n] != -1) return dp[n];
        dp[n] = Math.max(memoization(arr, n-1, dp), memoization(arr, n-2, dp) + arr[n]);
        return dp[n];
    }

    // Tabulation approach
    private static int tabulation(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);
        }
        return dp[n-1];
    }

    // Optimal Space approach
    private static int optimalSpace(int[] arr) {
        int n = arr.length;
        int first = arr[0];
        int second = Math.max(arr[0], arr[1]);
        for (int i=2; i<n; i++) {
            int temp = second;
            second = Math.max(second, first + arr[i]);
            first = temp;
        }
        return second;
    }
}
