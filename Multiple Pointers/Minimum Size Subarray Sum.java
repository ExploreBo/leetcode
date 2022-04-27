/*
brute force: O(n^2)
*/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    ans = Math.min(ans, j-i+1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

/*
binary search: O(nlogn)
each time trying to find the subarray with starting index of i,
seach for the value that equals or greater than (target + sum[i - 1]).
because when sum[x] >= target + sum[i - 1], then sum[x] - sum[i - 1] >= target.
which means the sum of i, i + 1, ... x is greater than target.
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        if (sums[nums.length - 1] < s) return 0;
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int l = i;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l)/2;
                if (sums[mid] - sums[i] + nums[i] == s) {
                    l = mid;
                    break;
                } else if (sums[mid] - sums[i] + nums[i] < s) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l >= sums.length) break;
            ans = Math.min(ans, l - i + 1);
        }
        
        return (ans == Integer.MAX_VALUE ? 0 : ans);
    }
}

/*
two pointers: O(n)
*/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (left < nums.length && sum >= target) {
                min = Math.min(min, i - left + 1);
                sum -= nums[left++];
            }

        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
