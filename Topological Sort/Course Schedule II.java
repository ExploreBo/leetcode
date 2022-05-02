// Topological Sorting
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // We start from courses that have no prerequisites.
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int addedNode = 0;
        while (queue.size() > 0) {
            Integer course = queue.remove();
            topologicalOrder[addedNode++] = course;

            if (adjList.containsKey(course)) {
                for (Integer neighbor : adjList.get(course)) {
                    indegree[neighbor]--;
                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        if (addedNode == numCourses) {
            return topologicalOrder;
        } else {
            return new int[0];
        }
    }
}