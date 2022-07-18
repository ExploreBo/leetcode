class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int index = 0;
        int max = 0;
        while (index < nums.length) {
            if (nums[index] == 1) {
                int count = 1;
                while (index + 1 < nums.length && nums[index + 1] == 1) {
                    count++;
                    index++;
                }    
                max = Math.max(max, count);
            }
            index++;
        }
        return max;
    }
}