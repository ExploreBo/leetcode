// two pass
class Solution {
    public void sortColors(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(i, index, nums);
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(i, index, nums);
                index++;
            }
        }       
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// one pass
class Solution {
    public void sortColors(int[] nums) {
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) { // index > p2 means we gonna check what has been swapped here as color2.
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                // we don't know what is swapped here, need revalidation
                index--;
            }
            index++;    
        }
    }
}


