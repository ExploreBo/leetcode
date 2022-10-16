class Solution {
    int min;
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        min = Integer.MAX_VALUE;
        swimInWaterHelper(grid, 0, 0, Integer.MIN_VALUE, new HashSet());
        return min;
    }

    private void swimInWaterHelper(int[][] grid, int row, int col, int max, Set<Integer> seen){
        if (col == grid.length - 1 && row == grid.length - 1) {
            min = Math.min(min, Math.max(max, grid[row][col]));
            return;
        }
        if (max > min) {
            return;
        }
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (0 <= newRow && newRow < grid.length && 0 <= newCol && newCol < grid.length && seen.add(grid[newRow][newCol])) {
                swimInWaterHelper(grid, newRow, newCol, Math.max(max, grid[row][col]), seen);
                seen.remove(grid[newRow][newCol]);
            }
        }
    }
}

// Dij algorithm. We always walk in the smallest one that is 4-directionally adjacent to ones we've visited.
class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((k1, k2) ->
                grid[k1 / N][k1 % N] - grid[k2 / N][k2 % N]);
        pq.offer(0);
        int ans = 0;

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        while (!pq.isEmpty()) {
            int k = pq.poll();
            int r = k / N, c = k % N;
            ans = Math.max(ans, grid[r][c]);
            if (r == N-1 && c == N-1) return ans;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N && !seen.contains(ck)) {
                    pq.offer(ck);
                    seen.add(ck);
                }
            }
        }

        throw null;
    }
}