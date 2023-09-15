// Q: Given and integer n and k, find the minimum number of attempts needed to find the critical floor in the worst case.
public class eggDropp_recursive {
    public static void main(String[] args) {
        int e = 3; // no of egges given
        int f = 5; // no of floors given
        System.out.println(solve(e, f));
    }

    private static int solve(int e, int f) {
        if (f <= 1 || e == 1) return f;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= f; i++) {
            int temp = 1 + Math.max(solve(e - 1, i - 1), solve(e, f - i));
            min = Math.min(min, temp);
        }
        return min;
    }
}
