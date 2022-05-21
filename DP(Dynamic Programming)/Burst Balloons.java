// explanation: https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
/*
Time complexity: O(N^3). There are O(N^2)states. 
For each state, determining the maximum coins requires iterating over all balloons in the range [left, right]. 
Thus the total time complexity is O(N^2) * O(N) = O(N^3).
*/
class Solution {
    public int maxCoins(int[] nums) {
        int[] extendedNums = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) if (x > 0) extendedNums[n++] = x;
        // extend two elements at farleft and farright, as the extended boundary.
        extendedNums[0] = extendedNums[n++] = 1;


        int[][] memo = new int[n][n];
        return burst(memo, extendedNums, 0, n - 1);        
    }
    
    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right] 
            + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }    
}