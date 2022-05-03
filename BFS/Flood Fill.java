class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int startColor = image[sr][sc];
        // the condition check is important to avoid infinite loop
        if (startColor != newColor) dfs(image, sr, sc, newColor, startColor);
        return image;
    }
    
    private void dfs(int[][] image, int row, int column, int newColor, int startColor) {
        if (image[row][column] == startColor) {
            image[row][column] = newColor;
            if (row >= 1) dfs(image, row - 1, column, newColor, startColor);
            if (row + 1 <image.length) dfs(image, row + 1, column, newColor, startColor);
            if (column >= 1) dfs(image, row, column - 1, newColor, startColor);
            if (column + 1 < image[0].length) dfs(image, row, column + 1, newColor, startColor);            
        }
    }
}