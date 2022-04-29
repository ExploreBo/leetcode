// brute forcec
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}

// hashmap + prefix sum
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int prefixSum = 0;
        int longestSubarray = 0;
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Check if all of the numbers seen so far sum to k.
            if (prefixSum == k) {
                longestSubarray = i + 1;
            }

            // If any subarray seen so far sums to k, then
            // update the length of the longest_subarray. 
            if (indices.containsKey(prefixSum - k)) {
                longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
            }
            
            // Only add the current prefix_sum index pair to the 
            // map if the prefix_sum is not already in the map.
            // this is to make sure it stores the farest index (leading to max length)
            if (!indices.containsKey(prefixSum)) {
                indices.put(prefixSum, i);
            }
        }
        
        return longestSubarray;
    }
}
