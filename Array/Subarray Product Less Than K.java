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
                // 2,3,5 -> [2], [2, 3], [2, 3, 5]
                count += right - left + 1;
            }
        }
        return count;
    }
}
