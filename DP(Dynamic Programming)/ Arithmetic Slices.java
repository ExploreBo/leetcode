/*
Reason behind: dp[i + 1] = dp[i] + 1.
the number of arithmetic slices of [1, 2, 3, ,4] and [2, 3, 4, 5] is the same.
plus one, i.e. [1, 2, 3, ,4 ,5].
*/
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }
}
