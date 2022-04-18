class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Initialize the map of key to frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){ map.put(num, map.getOrDefault(num, 0) + 1); }
        // max heap based on the frequency
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        // fill the heap
        for(int key : map.keySet()){ heap.add(key); }
        
        int[] result = new int[k];
        // poll the top k elements
        for(int i = 0; i < k; i++){
            result[i] = heap.poll();
        }
        
        return result;        
        
    }
}