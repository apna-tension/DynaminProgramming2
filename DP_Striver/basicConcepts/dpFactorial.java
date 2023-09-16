package basicConcepts;

import java.util.Scanner;

public class dpFactorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number : ");
        int n = sc.nextInt();
        sc.close();
        System.out.println("using recursion : " + recursive(n));
        int[] dp = new int[n+1];
        System.out.println("using memoization : " + memoized(n, dp));
        System.out.println("using tabulation : " + tabulation(n));
        System.out.println("using optimal space : " + optimalSpace(n));
    }

    private static int recursive(int n) {
        if (n <= 1) return 1;
        return n * recursive(n-1);
    }

    private static int memoized(int n, int[] dp) {
        if (n == 0 || n == 1) return 1;
        if (dp[n] != 0) return dp[n];
        dp[n] = n * memoized(n-1, dp);
        return dp[n];
    }

    private static int tabulation(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = i * dp[i-1];
        }
        return dp[n];
    }
    private static int optimalSpace(int n) {
        if (n <= 1) return 1;
        int prev = 1;
        int curr = prev;
        for (int i = 2; i <= n; i++) {
            curr = i * prev;
            prev = curr;
        }
        return curr;
    }
}
