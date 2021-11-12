class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int len = nums.length;
        while (i < len) {
            if (nums[i] != val) {
                i++;
            } else {
                nums[i] = nums[len - 1];
                len--;
            }
        }
        return len;
    }
}