/*
Since House[1] and House[n] are adjacent, they cannot be robbed together. 
Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], 
depending on which choice offers more money. Now the problem has degenerated to the House Robber, 
which is already been solved.
*/
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max1 = rob_simple(nums, 0, nums.length - 2);
        int max2 = rob_simple(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public int rob_simple(int[] nums, int start, int end) {
        int t1 = 0;
        int t2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }

        return t1;
    }
}
