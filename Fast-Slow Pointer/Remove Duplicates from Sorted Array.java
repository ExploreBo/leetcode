// brute force: O(n^2)
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 1) return 0;
        int count = 0;
        for (int i = 1; i < nums.length - count; i++) {
            if (nums[i] == nums[i - 1]) {
                int temp = nums[i];
                int j = i;
                while (j + 1 < nums.length - count) {
                    nums[j] = nums[j + 1];
                    j++;
                }
                nums[nums.length - count - 1] = temp;
                i--;
                count++;
            }
        }
        return nums.length - count;
    }
}

/*
Fast and slow pointers. Imagine do the operation for two arrays. Fast pointer works
on the original array while slow pointer works on the result array.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        // the insert index, meaning the index of next selected element to be inserted
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
            // if nums[i] == nums[i - 1], no need to insert any element, no new distint element was found
        }
        // return the length, need to plus 1
        return i + 1;
    }
}