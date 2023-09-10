
// Q:- Matrix Chain Multiplication using Recursion
public class recursiveMCM {
    public static void main(String[] args) {
        int[] arr = { 40, 20, 30, 10, 30 };
        int n = arr.length;
        System.out.println(solve(arr, 1, n - 1));
    }

    private static int solve(int[] arr, int i, int j) {
        if (i >= j) return 0;
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int tempMinCost = 
                    solve(arr, i, k)
                    + solve(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, tempMinCost);
        }
        return minCost;
    }
}