// Sort the words with the same frequency by their lexicographical order.
// Need to implement the comparator carefully
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // Initialize the map of key to frequency
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){ map.put(word, map.getOrDefault(word, 0) + 1); }
        // max heap based on the frequency
        PriorityQueue<String> heap = new PriorityQueue<>(
                 (a,b) -> map.get(a) == map.get(b) ? a.compareTo(b) : map.get(b) - map.get(a));
        
        // fill the heap
        for(String word : map.keySet()){ heap.add(word); }
        
        List<String> result = new ArrayList();
        // poll the top k elements
        for(int i = 0; i < k; i++){
            result.add(heap.poll());
        }
        
        return result;        
        
    }        
}