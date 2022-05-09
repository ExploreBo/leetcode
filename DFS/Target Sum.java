// brute force DPS: O(2^n)
public class Solution {
    int count = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }
}

// find remaining to be set as "-". "0" need to be handled separately. O(n^2)
class Solution {
    private int result = 0;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        int zeroCount = 0;
        int nonZeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] == 0) zeroCount++;
            if (nums[i] != 0) nonZeroCount++;
        }
        if (sum < target) return 0;
        int pow = (int) Math.pow(2, zeroCount);
        if (nonZeroCount == 1) return Math.abs(target) == Math.abs(sum) ? pow : 0;
        if (nonZeroCount == 0) return target == 0 ? pow : 0;

        helper(nums, 0, sum - target);
        return result * pow;
    }
    
    private void helper (int[] nums, int start, int remaining) {
        if (start >= nums.length) return;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            int diff = 2 * nums[i];
            if (diff == remaining) {
                this.result++;
            }
            if (diff < remaining) {
                helper(nums, i + 1, remaining - diff);
            }
        }
    }
}

// recursion with memorization
public class Solution {
    int total;
    
    public int findTargetSumWays(int[] nums, int S) {
        total = Arrays.stream(nums).sum();
        
        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate(nums, 0, 0, S, memo);
    }
    
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (memo[i][sum + total] != Integer.MIN_VALUE) {
                return memo[i][sum + total];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + total] = add + subtract;
            return memo[i][sum + total];
        }
    }
}

// 2D Array DP
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];
        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; sum++) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }
        
        return Math.abs(S) > total ? 0 : dp[nums.length - 1][S + total];
    }
}

