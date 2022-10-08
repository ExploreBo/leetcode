// BFS
public class Solution {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue < Integer > queue = new LinkedList < > ();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}



// DFS
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int num = 0;
        Map<Integer, List<Integer>> adj = new HashMap();
        for (int i = 0; i < isConnected.length; ++i) {
            adj.putIfAbsent(i, new ArrayList());
            for (int j = i; j < isConnected.length; ++j) {
                if (isConnected[i][j] == 1) {
                    adj.get(i).add(j);
                    adj.putIfAbsent(j, new ArrayList());
                    adj.get(j).add(i);
                }
            }
        }
        Set<Integer> seen = new HashSet();

        for (int i = 0; i < isConnected.length; ++i) {
            if (seen.add(i)) {
                Stack<Integer> stack = new Stack();
                stack.push(i);
                ++num;
                while (!stack.isEmpty()) {
                    int cur = stack.pop();
                    for (int neighbor : adj.get(cur)) {
                        if (seen.add(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
        return num;
    }
}