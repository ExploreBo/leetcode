class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList();
        int freshCount = 0;
        int minute = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i,j});
                }
            }
        }
        if (freshCount == 0) return 0; // important to eliminate the edge case of no rotten orange at all
        while (!queue.isEmpty()) {
            minute++;
            List<int[]> list = new ArrayList();
            while (!queue.isEmpty()) {
                list.add(queue.remove());
            }
            for (int[] curr : list) {
                if (curr[0] - 1 >= 0 && grid[curr[0] - 1][curr[1]] == 1) {
                    freshCount--;
                    grid[curr[0] - 1][curr[1]] = 2;
                    queue.add(new int[]{curr[0] - 1, curr[1]});
                }
                if (curr[0] + 1 < grid.length && grid[curr[0] + 1][curr[1]] == 1) {
                    freshCount--;
                    grid[curr[0] + 1][curr[1]] = 2;
                    queue.add(new int[]{curr[0] + 1, curr[1]});                    
                }
                if (curr[1] - 1 >= 0 && grid[curr[0]][curr[1] - 1] == 1) {
                    freshCount--;
                    grid[curr[0]][curr[1] - 1] = 2;
                    queue.add(new int[]{curr[0], curr[1] - 1});                      
                }
                if (curr[1] + 1 < grid[0].length && grid[curr[0]][curr[1] + 1] == 1) {
                    freshCount--;
                    grid[curr[0]][curr[1] + 1] = 2;
                    queue.add(new int[]{curr[0], curr[1] + 1});                      
                }
            }
            
        }
        return freshCount == 0 ? minute - 1 : -1;
    }
}


// follow up: make the space complexity O(1) by tracking the next level elements to be processed in place isntead of maintaining a queue.