/*
Youtube of Bucket Sort: https://www.youtube.com/watch?v=YPTqKIgVk-k
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        int count = 0;
        // Initialize the map of key to frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){ map.put(num, map.getOrDefault(num, 0) + 1); }
        
        // create array of list at n + 1 length to store the mapping of frequency to nums
        // n + 1 as length because at most the num could appear that amout of times
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            if (bucket[map.get(key)] == null) {
                bucket[map.get(key)] = new ArrayList();
            }
            bucket[map.get(key)].add(key);
        }
        
        for (int i = nums.length; i > 0; i--) {
            if (bucket[i] != null) {
                for (Integer j : bucket[i]) {
                    result[count] = j;
                    count++;
                    if (count == k) {
                        return result;
                    }
                }
            }
        }
        return result;        
        
    }
}