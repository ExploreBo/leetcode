// DP: time complexity n^2
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}


/*
DP + binary search: time complexity nlogn
Patience Sort: https://www.youtube.com/watch?v=l2rCz7skAlk
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            int pos = binarySearch(dp,len,nums[i]);
            // replace with a smaller element
            if (nums[i] < dp[pos]) dp[pos] = nums[i];
            // extend the dp
            if (pos > len) {
                len = pos;
                dp[len] = nums[i];
            }
        }
        return len+1;
    }
    // not to find the exact value, but to find the expected position
    private int binarySearch(int[] dp, int len, int val) {
        int left = 0;
        int right = len;
        while(left + 1 < right) {
            int mid = left + (right-left) / 2;
            if (dp[mid] == val) {
                return mid;
            } else {
                if (dp[mid] < val) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (dp[right] < val) return len + 1;
        else if (dp[left] >= val) return left;
        else return right;
    }
}