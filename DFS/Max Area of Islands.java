// DFS + recursive
class Solution {
    int max;
    int currentCount;
    int m;
    int n;
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        max = 0;
        Set<Integer> seen = new HashSet();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                currentCount = 0;
                getIslandArea(grid, seen, i, j);
                max = Math.max(max, currentCount);
            }
        }
        return max;
    }

    private void getIslandArea(int[][] grid, Set<Integer> seen, int row, int column) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (0 <= row && row < m && 0 <= column && column < n) {
            if (seen.add(row * n + column) && grid[row][column] == 1) {
                ++currentCount;
                for (int[] direction : directions) {
                    getIslandArea(grid, seen, row + direction[0], column + direction[1]);
                }
            }
        } 
    }
}

// DFS + iterative. recursion => stack.
class Solution {
    int max;
    int currentCount;
    int m;
    int n;
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        max = 0;
        Set<Integer> seen = new HashSet();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seen.add(i * n + j) && grid[i][j] == 1) {
                    currentCount = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        currentCount++;
                        int[] position = stack.pop();
                        for (int[] direction : directions) {
                            int row = position[0] + direction[0];
                            int col = position[1] + direction[1];
                            if (0 <= row && row < m && 0 <= col && col < n && seen.add(row * n + col) && grid[row][col] == 1) {
                                stack.push(new int[]{row, col});
                            }
                        }
                    }
                    max = Math.max(max, currentCount);                    
                }
            }
        }
        return max;
    }
}
