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

// Quick Sort
class Solution {
    public int[] sortArray(int[] nums) {
        sort(0, nums.length-1, nums);
        return nums;
    }
    
    private void sort(int lo, int hi, int[] nums) {
        if (lo >= hi) return;
        int j = partition(lo, hi, nums);
        sort(lo,j-1,nums);
        sort(j+1,hi,nums);
    }
    
    private int partition(int lo, int hi, int[] nums) {
        int i = lo; int j = hi+1;
        while (true) {
            while(nums[++i] < nums[lo]) if (i == hi) break;
            while(nums[--j] > nums[lo]) if (j == lo) break;
            if (i >= j) break;
            swap(i,j,nums);
        }
        swap(lo,j,nums);                
        return j;
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}