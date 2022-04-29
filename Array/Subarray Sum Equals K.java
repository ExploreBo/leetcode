// brute force
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;       
    }
}

// hashmap + prefix sum
class Solution {
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        int count = 0;
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Check if all of the numbers seen so far sum to k.
            if (prefixSum == k) {
                count++;
            }

            if (indices.containsKey(prefixSum - k)) {
                count += indices.get(prefixSum - k);
            }
            

            indices.put(prefixSum, indices.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;        
    }
}