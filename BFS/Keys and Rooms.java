// BFS
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int roomCount = rooms.size();
        int visitedCount = 1;
        boolean[] visited = new boolean[roomCount];
        Queue<List<Integer>> queue = new LinkedList();
        queue.add(rooms.get(0));
        visited[0] = true;
        while (!queue.isEmpty()) {
            List<Integer> keys = queue.remove();
            for (int key : keys) {
                if (!visited[key]) {
                    visitedCount++;
                    visited[key] = true;
                    queue.add(rooms.get(key));
                }
            }
        }
        return visitedCount == roomCount;
    }
}