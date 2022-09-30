class Solution {
    public boolean removeOnes(int[][] grid) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == 1) {
                filp(j, grid);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j - 1] != grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    private void filp(int column, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            grid[i][column] = 1 - grid[i][column];
        }
    }
}



/*
Both rows have to follow the same patterns for us to be able to flip them into all 0s.
and by the same patterns, I mean for any two rows, they either have to be

Exactly the same
Exactly opposite
*/
class Solution {
    public boolean removeOnes(int[][] grid) {
        for (int[] g : grid){
            for (int i = 0; i < g.length; i++){
                if (Math.abs(g[i] - grid[0][i]) != Math.abs(g[0] - grid[0][0]))
                    return false;
            }
        }
        return true;
    }
}