// brute force O(n)
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        int answer = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum < k) {
                    answer = Math.max(answer, sum);
                }
            }
        }
        return answer;
    }
}

// sort + binary search
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        int answer = -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            int idx = Arrays.binarySearch(nums, i + 1, nums.length, k - nums[i] - 1);
            int j = (idx >= 0 ? idx : ~idx);
            if (j == nums.length || nums[j] > k - nums[i] - 1) {
                j--;
            }
            if (j > i) {
                answer = Math.max(answer, nums[i] + nums[j]);
            }
        }
        return answer;
    }
}

// sort + two pointers
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int answer = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                answer = Math.max(answer, sum);
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }
}

/*
Counting Sort: levrage the contraint of input elemnet is within [1, 1000].
create a new array to count it and it is sorted.

*/ 
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        int answer = -1;
        int[] count = new int[1001];
        for (int num : nums) {
            count[num]++;
        }
        int lo = 1;
        int hi = 1000;
        while (lo <= hi) {
            if (lo + hi >= k || count[hi] == 0) {
                hi--;
            } else {
            	// Note that the result can be a sum of two identical numbers, // and that means that lo can be equal to hi. In this case, we // need to check if the count for that number is greater than one.
                if (count[lo] > (lo < hi ? 0 : 1)) {
                    answer = Math.max(answer, lo + hi);
                }
                lo++;
            }
        }
        return answer;
    }
}