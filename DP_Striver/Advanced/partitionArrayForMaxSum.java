package Advanced;

import java.util.Arrays;

// Q: - Given an array of integers arr and an integer k. Find the maximum sum of a partition of the array such that each element in the partition is less than or equal to k.
// - A partition is a contiguous subarray.
// - Example 1:
// - Input: arr = [1,15,7,9,2,5,10], k = 3
// - Output: 84
// - Explanation: arr becomes [1,15,7,9,2,5,10] after partitioning it into [1,15,7], [9], [2,5,10].
// - Each element is less than or equal to 3 so the sum is max(23,9,17) = 84.
// - Example 2:
// - Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
// - Output: 83
// - Constraints:
// - 1 <= arr.length <= 500
// - 0 <= arr[i] <= 109
// - 0 <= k <= arr.length

// leetcode : - https://leetcode.com/problems/partition-array-for-maximum-sum/
// gfg: - https://www.geeksforgeeks.org/partition-array-maximum-sum/

public class partitionArrayForMaxSum {
    public static void main(String[] args) {
        int[] arr = { 1, 15, 7, 9, 2, 5, 10 };
        int k = 3;

        // tabulation
        System.out.println(maxSumAfterPartitioning(arr, k));

        // recursion
        System.out.println(maxSumAfterPartitioning(arr, k, 0));

        // memoization
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        System.out.println(maxSumAfterPartitioning(arr, k, 0, dp));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
                max = Math.max(max, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + max * j);
            }
        }

        return dp[n - 1];
    }

    // solve by recursion
    public static int maxSumAfterPartitioning(int[] arr, int k, int i) {
        // base case when we reach the end of array return 0 instead of -infinity as we
        // are using max function in recursion call and it will return 0 if we return
        // -infinity from here as it is greater than -infinity and we will get wrong
        // answer in that case so we return 0
        if (i >= arr.length)
            return 0;

        // max will store the maximum element in the current window of size k
        int max = 0;
        int ans = 0;

        // we will try all the possible windows of size k and find the maximum sum
        for (int j = 1; j <= k && i + j <= arr.length; j++) {
            // update the maximum element in the current window of size k if we find a
            // greater element than the current maximum element in the window of size k then
            // update the maximum element in the window of size k to the new maximum element
            // in the window of size k.
            max = Math.max(max, arr[i + j - 1]);

            // we will find the maximum sum by taking the maximum of the current maximum sum
            // and the sum of the maximum element in the current window of size k and the
            // maximum sum of the remaining array after the current window of size k.
            ans = Math.max(ans, max * j + maxSumAfterPartitioning(arr, k, i + j));
        }
        return ans;
    }


    // solve by memoization
    public static int maxSumAfterPartitioning(int[] arr, int k, int i, int[] dp) {
        // base case when we reach the end of array return 0
        if (i >= arr.length)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int max = 0;
        int ans = 0;
        for (int j = 1; j <= k && i + j <= arr.length; j++) {
            max = Math.max(max, arr[i + j - 1]);
            ans = Math.max(ans, max * j + maxSumAfterPartitioning(arr, k, i + j, dp));
        }
        return dp[i] = ans;
    }
}