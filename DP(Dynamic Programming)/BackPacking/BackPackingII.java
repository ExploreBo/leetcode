/*
Problem: https://www.lintcode.com/problem/125
*/
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */

    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        int[][] dp = new int[A.length + 1][m + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[(i - 1)][j];
                if (j >= A[i - 1]) {
                    // compare between two options (with item i - 1 or not) and use the larger one
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[(i - 1)][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[A.length][m];
    }
}