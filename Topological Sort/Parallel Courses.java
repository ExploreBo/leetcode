class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap();
        Map<Integer, Integer> indegrees = new HashMap();
        // initialize the indegrees map
        for (int i = 1; i <= n; ++i) {
            graph.put(i, new ArrayList());
            indegrees.put(i, 0);
        }
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            indegrees.put(relation[1], indegrees.get(relation[1]) + 1);
        }
        Queue<Integer> availableCourses = new LinkedList();
        Set<Integer> takenCourses = new HashSet();
        for (int key : indegrees.keySet()) {
            if (indegrees.get(key) == 0) availableCourses.add(key);
        }

        int count = 0;       
        while (!availableCourses.isEmpty()) {
            count++;
            int availableSize = availableCourses.size();
            while (availableSize > 0) {
                int course = availableCourses.poll();
                if (takenCourses.add(course)) {
                    List<Integer> nextCourses = graph.get(course);
                    for (int nextCourse : nextCourses) {
                        indegrees.put(nextCourse, indegrees.get(nextCourse) - 1);
                        if (indegrees.get(nextCourse) == 0 && !takenCourses.contains(nextCourse)) {
                            availableCourses.add(nextCourse);
                        }
                    }
                } else {
                    continue;
                }
                --availableSize;
            }
        }
        return takenCourses.size() == n ? count : -1;

        
    }
}
