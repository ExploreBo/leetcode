// move the first m elements of nums1 all the way to the end and start from beginning with two pointers on each array.
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1; i >= 0; i--) {
            nums1[i + n] = nums1[i];
        }
        int index1 = n;
        int index2 = 0;
        int count = 0;
        while (index1 < m + n && index2 < n) {
            if (nums1[index1] <= nums2[index2]) {
                nums1[count] = nums1[index1++];
            } else {
                nums1[count] = nums2[index2++];
            }
            count++;
        }
        while (index2 < n) {
            nums1[count++] = nums2[index2++];
        }
    }
}

// Start from the end of each array since the last n postions in nums1 could be overwritten.
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;
        
        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}