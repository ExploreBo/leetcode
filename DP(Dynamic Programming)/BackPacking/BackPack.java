/*
Problem: https://www.lintcode.com/problem
Youtube: https://www.youtube.com/watch?v=O31ULJTv-zw
*/
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
    	// dp[i][j]: whether we could use the first i items to reach j size.
        boolean dp[][] = new boolean[A.length + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i-1] && dp[i-1][j - A[i-1]]) {
                    dp[i][j] = true;
                }
            } // for j
        } // for i
        
        for (int i = m; i >= 0; i--) {
            if (dp[A.length][i]) {
                return i;
            }
        }
        
        return 0;
    }
}