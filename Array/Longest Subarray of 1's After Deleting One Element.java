// the same as https://leetcode.com/problems/max-consecutive-ones-iii/. just k = 1 and return max - 1.

class Solution {
    public int longestSubarray(int[] nums) {
      int start = 0;
      int end = 0;
      int max = 0;
      int[] count = new int[2];
      while (end < nums.length) {
          count[nums[end]]++;
          while (count[0] > 1 && start <= end) {
              count[nums[start]]--;
              start++;
          }
          max = Math.max(max, end - start + 1);
          end++;
      }
      return max - 1;         
    }
}