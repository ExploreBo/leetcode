/* Approach 1: BFS from Empty Land to All Houses
Optimization:
 If we cannot reach all the houses from the current empty cell, then it is not a valid empty cell. 
 Furthermore, we can be certain that any cell visited during this BFS also cannot reach all of the houses. 
 So we will mark all cells visited during this BFS as obstacles to ensure that we do not start another BFS from this region.

Time complexity: O(M^2 * N^2)

*/
class Solution {
    private int bfs(int[][] grid, int row, int col, int totalHouses) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;
        int distanceSum = 0;
        int housesReached = 0;
        
        // Queue to do a bfs, starting from (row, col) cell.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ row, col });
        
        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;
        while (!q.isEmpty() && housesReached != totalHouses) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];
                
                // If this cell is a house, then add the distance from source to this cell
                // and we go past from this cell.
                if (grid[row][col] == 1) {
                    distanceSum += steps;
                    housesReached++;
                    continue;
                }
                
                // This cell was empty cell, hence traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0]; 
                    int nextCol = col + dir[1];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[]{ nextRow, nextCol });
                        }
                    }
                }
            }
            
            // After traversing one level of cells, increment the steps by 1 to reach to next level.
            steps++;
        }

        // If we did not reach all houses, then any cell visited also cannot reach all houses.
        // Set all cells visted to 2 so we do not check them again and return MAX_VALUE.
        if (housesReached != totalHouses) {
            for (row = 0; row < rows; row++) {
                for (col = 0; col < cols; col++) {
                    if (grid[row][col] == 0 && vis[row][col]) {
                        grid[row][col] = 2;
                    }
                }
            }
            return Integer.MAX_VALUE;
        }
        
        // If we have reached all houses then return the total distance calculated.
        return distanceSum;
    }
    
    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length; 
        int cols = grid[0].length;
        int totalHouses = 0;
        
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                }
            }
        }
        
        // Find the min distance sum for each empty cell.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 0) {
                    minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses));
                }
            }
        }
        
        // If it is impossible to reach all houses from any empty cell, then return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        
        return minDistance;
    }
}

/* 

Approach 2: BFS from Houses to Empty Land
When there are fewer houses than empty lands, then this approach will require less time than the previous approach and vice versa. 
While, on average, this approach is not an improvement on the previous approach.

*/
class Solution {
    private void bfs(int[][] grid, int[][][] distances, int row, int col) {
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;

        // Use a queue to do a bfs, starting from each cell located at (row, col).
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ row, col });

        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];

                // If we reached an empty cell, then add the distance
                // and increment the count of houses reached at this cell.
                if (grid[row][col] == 0) {
                    distances[row][col][0] += steps;
                    distances[row][col][1] += 1;
                }

                // Traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[]{ nextRow, nextCol });
                        }
                    }
                }
            }

            // After traversing one level cells, increment the steps by 1.
            steps++;
        }
    }

    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;

        // Store { total_dist, houses_count } for each cell.
        int[][][] distances = new int[rows][cols][2];

        // Count houses and start bfs from each house.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                    bfs(grid, distances, row, col);
                }
            }
        }

        // Check all empty lands with houses count equal to total houses and find the min ans.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (distances[row][col][1] == totalHouses) {
                    minDistance = Math.min(minDistance, distances[row][col][0]);
                }
            }
        }

        // If we haven't found a valid cell return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }
}

/* 
Approach 3: BFS from Houses to Empty Land (Optimized)

Instead of making a new matrix each time to track the visited cells,
 we can use the input grid matrix for this purpose. 
 During the first BFS we can change the visited empty land values from 0 to -1. 
 Then during the next BFS we will only visit empty lands with a value of -1s (meaning they can reach the first house), 
 then change -1 to -2 and then perform the next BFS only on -2s, and so on...
*/
class Solution {
    public int shortestDistance(int[][] grid) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Total Mtrix to store total distance sum for each empty cell.
        int[][] total = new int[rows][cols];

        int emptyLandValue = 0;
        int minDist = Integer.MAX_VALUE;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                // Start a BFS from each house.
                if (grid[row][col] == 1) {
                    minDist = Integer.MAX_VALUE;

                    // Use a queue to perform a BFS, starting from the cell at (r, c).
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{ row, col });

                    int steps = 0;

                    while (!q.isEmpty()) {
                        steps++;

                        for (int level = q.size(); level > 0; --level) {
                            int[] curr = q.poll();

                            for (int[] dir : dirs) {
                                int nextRow = curr[0] + dir[0];
                                int nextCol = curr[1] + dir[1];

                                // For each cell with the value equal to empty land value
                                // add distance and decrement the cell value by 1.
                                if (nextRow >= 0 && nextRow < rows &&
                                    nextCol >= 0 && nextCol < cols &&
                                    grid[nextRow][nextCol] == emptyLandValue) {
                                    grid[nextRow][nextCol]--;
                                    total[nextRow][nextCol] += steps;

                                    q.offer(new int[]{ nextRow, nextCol });
                                    minDist = Math.min(minDist, total[nextRow][nextCol]);
                                }
                            }
                        }
                    }

                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}