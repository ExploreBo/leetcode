// DFS
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // island count + 1, clear all the rest as '0'
                    clearRestOfLand(i, j, grid);
                }
            }
        }
        return count;
    }
    
    private void clearRestOfLand(int i, int j, char[][]grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';        
        clearRestOfLand(i - 1, j, grid);
        clearRestOfLand(i + 1, j, grid);
        clearRestOfLand(i, j - 1, grid);
        clearRestOfLand(i, j + 1, grid);
    }
}

//  BFS
class Solution {
    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i < grid.length;i++)
            for(int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] =='1') {
                    bfsFill(grid,i,j);
                    count++;
                }
            }
        return count;
    }
    private void bfsFill(char[][] grid,int x, int y) {
        grid[x][y] = '0';
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> queue = new LinkedList<Integer>();  
        int code = x * m + y;  
        queue.offer(code);  
        while (!queue.isEmpty()) {  
            code = queue.poll();  
            int i = code / m;  
            int j = code % m;  
            if (i > 0 && grid[i - 1][j] =='1') {   //search upward and mark adjacent '1's as '0'.
                queue.offer((i - 1) * m + j);  
                grid[i - 1][j]='0';  
            }  
            if (i < n - 1 && grid[i + 1][j] =='1') {  //down
                queue.offer((i + 1) * m + j);  
                grid[i + 1][j]='0';  
            }  
            if (j > 0 && grid[i][j - 1] =='1') {  //left
                queue.offer(i * m + j - 1);  
                grid[i][j - 1]='0';  
            }  
            if (j < m - 1 && grid[i][j + 1] =='1') {  //right
                queue.offer(i * m + j + 1);  
                grid[i][j + 1]='0';  
            }
        } 
    }
}