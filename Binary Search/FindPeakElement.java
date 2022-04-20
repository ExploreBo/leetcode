/*
Solution: O(n)
The left is negative infitity, so each time starting from index 0. 
If it is greater than the next, it is a peak.
Because each value is unique, if it is not greater than, then it is
smaller than.
*/
class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }
}

/*
Solution: O(logn)
Simplify the question by assuming there is only one peak.
Draw picture of slope and come to the conclusion.
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            }
            if (nums[mid] >= nums[mid + 1]) {
                r = mid;    
            }
        }
        return l;
    }
}