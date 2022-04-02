// Merge Sort
class Solution {
    public int[] sortArray(int[] nums) {
        return mergeSort(0, nums.length - 1, nums);
    }
    
    private int[] mergeSort(int left, int right, int[] nums) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            return merge(mergeSort(left, mid, nums), mergeSort(mid + 1, right, nums));
        }
        return new int[]{nums[left]};
    }
    
    
    private int[] merge(int[] array1, int[] array2) {
        int i = 0, j = 0;
        int[] result = new int[array1.length + array2.length];
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                result[i + j] = array1[i];
                i++;
            } else {
                result[i + j] = array2[j];
                j++;                
            }
        }
        while (i < array1.length) {
            result[i + j] = array1[i];
            i++;
        }
        while (j < array2.length) {
            result[i + j] = array2[j];
            j++;                
        }
        return result;
    }
    
    
}