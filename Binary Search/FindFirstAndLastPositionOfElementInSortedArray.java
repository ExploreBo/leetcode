class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {return new int[]{-1, -1};};
        return new int[]{findFirst(nums, target), findLast(nums, target)};
    }
    
    private int findFirst(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            int mid = (start + end) / 2;
            if (nums[mid] < target){
                start = mid + 1;
            } else{
                end = mid;
            }
        }
        return nums[start] == target ? start : -1;
    }

    private int findLast(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            // + 1 to avoid infinite loop
            int mid = (start + end + 1) / 2;
            if (nums[mid] <= target){
                start = mid;
            } else{
                end = mid - 1;
            }
        }
        return nums[start] == target ? start : -1;
    } 
}