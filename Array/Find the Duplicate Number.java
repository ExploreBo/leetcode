class Solution {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length;) {
            if (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
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


/* Solution of O(1) space without mofifying nums:
Due to duplicate, there would be a cycle.
So use hare and tortoise way, to find the entrance/start of the cycle. That would be the duplicate.
*/
