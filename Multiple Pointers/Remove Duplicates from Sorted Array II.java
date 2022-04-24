// Similar to I
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) return nums.length;
        // the insert index, meaning the index of next selected element to be inserted
        int i = 1;
        for (int j = 2; j < nums.length; j++) {
            if (nums[j] != nums[i] || nums[j - 1] !=nums[i - 1]) {
                i++;
                nums[i] = nums[j];
            }
            // otherwise no need to insert any element, no new distint element was found
        }
        return i + 1;        
    }
}

/*
This is more scalable. No matter how many times an element is allowed to duplicate.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        // Initialize the counter and the slow pointer.
        int i = 1, count = 1;
        // Start from the second element of the array and process
        // elements one by one.
        for (int j = 1; j < nums.length; j++) {
            // If the current element is a duplicate, increment the count.
            //
            if (nums[j] == nums[j - 1]) {
                count++;
            } else {
                // Reset the count since we encountered a different element
                // than the previous one.
                count = 1;
            }
            
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            if (count <= 2) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}