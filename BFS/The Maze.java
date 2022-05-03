// BFS
// Time complexity: O(m∗n)
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{start[0], start[1]});
        while (queue.size() > 0) {
            int[] currPosition = queue.remove();
            if (visited[currPosition[0]][currPosition[1]]) {
                continue;
            }
            if (currPosition[0] == destination[0] && currPosition[1] == destination[1]) {
                return true;
            }
            visited[currPosition[0]][currPosition[1]] = true;
            int r = currPosition[1] + 1;
            int l = currPosition[1] - 1;
            int u = currPosition[0] - 1;
            int d = currPosition[0] + 1;
            // going right
            while (r < maze[0].length && maze[currPosition[0]][r] == 0) {
                r++;
            }
            queue.add(new int[]{currPosition[0], r - 1});

            // going left
            while (l >= 0 && maze[currPosition[0]][l] == 0) {
                l--;
            }
            queue.add(new int[]{currPosition[0], l + 1});

            // going up
            while (u >= 0 && maze[u][currPosition[1]] == 0) {
                u--;
            }
            queue.add(new int[]{u + 1, currPosition[1]});

            // going down
            while (d < maze.length && maze[d][currPosition[1]] == 0) {
                d++;
            }
            queue.add(new int[]{d - 1, currPosition[1]});           
        }
        return false;
    }
}


// DFS
// Time complexity: O(m∗n)
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    
    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1;
        int l = start[1] - 1;
        int u = start[0] - 1;
        int d = start[0] + 1;
        // going right
        while (r < maze[0].length && maze[start[0]][r] == 0) {
            r++;
        }
        if (dfs(maze, new int[]{start[0], r - 1}, destination, visited)) {
            return true;
        }
        // going left
        while (l >= 0 && maze[start[0]][l] == 0) {
            l--;
        }
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited)) {
            return true;
        }
        // going up
        while (u >= 0 && maze[u][start[1]] == 0) {
            u--;
        }
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited)) {
            return true;
        }
        // going down
        while (d < maze.length && maze[d][start[1]] == 0) {
            d++;
        }
        if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited)) {
            return true;
        }
        return false;        
    }
}