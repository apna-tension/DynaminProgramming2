
package basicConcepts;
// fibonacci series using recursion, memoization and tabulation
import java.util.Scanner;
import java.util.Arrays;

public class fibonacii_all {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number : ");
        int n = sc.nextInt();
        sc.close();
        System.out.println("Recursin : " + fibRecursive(n));
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println("Memoization : " + fibMemoized(n, dp));
        System.out.println("Tabulation : " + fibTabulation(n));
        System.out.println("Optimal Space : " + optimalSpace(n));
    }

    private static int fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n-1) + fibRecursive(n-2);
    }

    private static int fibMemoized(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        dp[n] = fibMemoized(n-1, dp) + fibMemoized(n-2, dp);
        return dp[n];
    }

    private static int fibTabulation(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        // for(int i = 0; i <= n; i++) {
        //     System.out.println(fib[i]);
        // }
        // System.out.println("The N'th Fibonacci is " + fib[n]);
        return dp[n];
    }

    private static int optimalSpace(int n) {
        if (n <= 1) return n;
        int prev1 = 1;
        int prev2 = 0;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = prev1 + prev2;
            prev2 = prev1;
            prev1 = res;
        }
        return res;
    }

}