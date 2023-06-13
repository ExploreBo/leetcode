// sorting. Time complexity is O(nlogn)
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return nums[0];
        }
        Arrays.sort(nums);

        if (nums[n / 2] == nums[n / 2 + 1]) {
            return nums[n / 2];
        } else {
            return nums[n / 2 - 1];
        }
        
    }
}

// another solution is using map to store the frequency of each item. The space complexity is O(n)

// Voting and DownVoting Algorithm. After voting and devoting, the last current value should still be the majority value.
class Solution {
    public int majorityElement(int[] nums) {
        int vote = 1;
        int current = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == current) {
                ++vote;
            } else {
                --vote;
            }
            if (vote == 0) {
                vote = 1;
                current = nums[i];
            }
        }
        return current;
    }
}