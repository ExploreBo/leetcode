class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
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
      return max;         
        
    }
}