class Solution {
    public int minPathSum(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int[][] result = new int[rowCount][colCount];
        result[0][0] = grid[0][0]; 
        for (int i = 1; i < colCount; i++) {
            result[0][i] = result[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < rowCount; j++) {
            result[j][0] = result[j - 1][0] + grid[j][0];
        }
        for (int i = 1; i < rowCount; i++) {
            for (int j = 1; j < colCount; j++) {
                result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + grid[i][j];
            }
        }
        return result[rowCount - 1][colCount - 1];
    }
}

// utilize the existing grid matrix, no extra space
class Solution {
    public int minPathSum(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);    
                }
            }
        }
        return grid[rowCount - 1][colCount - 1];
    }
}