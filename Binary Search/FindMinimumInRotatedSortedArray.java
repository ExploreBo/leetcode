class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0, right = nums.length - 1;
        
        // draw the line picture and find the pattern

        while (left < right - 1) {           
            int mid = left + (right -left) / 2;
            
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
}