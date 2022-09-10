class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Integer> indegrees = new HashMap();
        Map<Integer, List<Integer>> graph = new HashMap();
        List<Integer> result = new ArrayList();
        if (n <= 0) return result;
        // initilate the graph and indegrees
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList());
            indegrees.put(i, 0);
        }
        
        if (n == 1) {
            return Arrays.asList(0);
        }
        // build indgrees and graph
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            indegrees.put(edge[0], indegrees.get(edge[0]) + 1);
            indegrees.put(edge[1], indegrees.get(edge[1]) + 1);
        }
        
        Queue<Integer> leaves = new LinkedList();
        for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 1) {
                leaves.add(entry.getKey());
            }
        }
        int totalNodes = n;
        while (totalNodes > 2) {
            int size = leaves.size();
            for (int i = 0; i < size; i++) {
                int leave = leaves.poll();
                List<Integer> children = graph.get(leave);
                for (int child : children) {
                    indegrees.put(child, indegrees.get(child) - 1);
                    if (indegrees.get(child) == 1) {
                        leaves.add(child);
                    }
                }
                totalNodes--;
            }
        }
        while (!leaves.isEmpty()) {
            result.add(leaves.poll());
        }
        return result;
    }
}