class Solution {
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length;) {
            if (nums[i] != i && nums[i] < nums.length) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }      
}