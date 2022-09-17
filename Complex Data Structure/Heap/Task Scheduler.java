class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n <= 0) {
            return tasks.length;
        }
        Map<Character, Integer> map = new HashMap();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(map.entrySet());
        int result = 0;
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList();
        while (!maxHeap.isEmpty()) {
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    Map.Entry<Character, Integer> entry = maxHeap.poll();
                    if (entry.getValue() > 1) {
                        entry.setValue(entry.getValue() - 1);
                        queue.offer(entry);     
                    }
                }
                if (!queue.isEmpty() || !maxHeap.isEmpty()) {
                    result++;
                }                
            }
            if (!queue.isEmpty()) {
                maxHeap.addAll(queue);
                queue.clear();                
            }
        }
        return result + 1;
    }
}