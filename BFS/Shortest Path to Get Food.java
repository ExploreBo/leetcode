/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
*/
class Solution {
    public int getFood(char[][] grid) {
        Set<Integer> food = new HashSet();
        Set<Integer> seen = new HashSet();
        int m = grid.length;
        int n = grid[0].length;
        // four directions we can move:   up,     down,     right,  left
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[] start = new int[2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '#') {
                    food.add(i * n + j);
                }
                if (grid[i][j] == '*') {
                    start = new int[]{i, j};
                }
            }
        }

        Deque<int[]> queue = new LinkedList();
        queue.add(start);
        seen.add(start[0] * n + start[1]);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.pollFirst();
                int row = cur[0];
                int col = cur[1];
                if (food.contains(row * n + col)) {
                    return level; 
                }
                for (int[] direction : directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if (0 <= nextRow && nextRow < m && 0 <= nextCol && nextCol < n) {
                        if (grid[nextRow][nextCol] != 'X' && !seen.contains(nextRow * n + nextCol)) {
                            queue.add(new int[]{nextRow, nextCol});
                            seen.add(nextRow * n + nextCol);
                        }                        
                    }

                }
            }
            level++;
        }
        return -1;
    }
}