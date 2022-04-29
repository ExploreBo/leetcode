class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        int left = 0;
        int product = 1;
        for (int right = 0; right < n; right++) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            if (product < k) {
                // each time add right - left + 1, means itself, itself and the previous one, itself previous and pre-previous one, and so on until the left
                count += right - left + 1;
            }
        }
        return count;
    }
}
