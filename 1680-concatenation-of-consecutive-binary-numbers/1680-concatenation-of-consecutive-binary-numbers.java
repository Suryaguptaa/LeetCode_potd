public class Solution {

    static final int MOD = 1_000_000_007;

    public int concatenatedBinary(int n) {
        return helper(1, n, 0);
    }

    private int helper(int i, int n, long result) {
        if (i > n) return (int) result;

        int bits = 32 - Integer.numberOfLeadingZeros(i);

        result = ((result << bits) % MOD + i) % MOD;

        return helper(i + 1, n, result);
    }
}