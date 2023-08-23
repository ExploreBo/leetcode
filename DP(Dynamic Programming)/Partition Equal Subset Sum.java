// 2^n -> m * n with memorization
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        Boolean[][] memo = new Boolean[nums.length + 1][sum / 2 + 1];
        return helper(nums, 0, sum / 2, memo);
    }

    private boolean helper(int[] nums, int start, int target, Boolean[][] memo) {
        if (target == 0) {
            return true;
        }
        if (start >= nums.length || target < 0) {
            return false;
        }
        if (memo[start][target] != null) {
            return memo[start][target];
        }
        boolean result = helper(nums, start + 1, target, memo) || helper(nums, start + 1, target - nums[start], memo);
        memo[start][target] = result;
        return result;
    }
}


// bottom-up, dp[i][j]=true if the sum j can be formed by array elements in subset nums[0]..nums[i],otherwise dp[i][j]=false
class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd, it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][subSetSum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subSetSum; j++) {
                if (j < curr)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
            }
        }
        return dp[n][subSetSum];
    }
}