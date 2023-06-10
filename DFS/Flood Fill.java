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


// BFS Solution
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] != color) {
            int column = image[0].length;
            int row = image.length;
            int total = row * column - 1;
            int startColor = image[sr][sc];
            Set<Integer> filled = new HashSet();
            Queue<Integer> toBeFilled = new LinkedList();
            toBeFilled.add(sr * column + sc);
            while (!toBeFilled.isEmpty()) {
                int current = toBeFilled.poll();
                if (!filled.contains(current)) {
                    // fill current pixel
                    image[current / column][current % column] = color;
                    filled.add(current);
                    // right
                    int right = current + 1;
                    if (current % column < column - 1 && !filled.contains(right) && image[right / column][right % column] == startColor) {
                        toBeFilled.add(right);
                    }
                    // left
                    int left = current - 1;
                    if (current % column > 0 && !filled.contains(left) && image[left / column][left % column] == startColor) {
                        toBeFilled.add(left);
                    }
                    // down
                    int down = current + column;
                    if (current / column < row - 1 && !filled.contains(down) && image[down / column][down % column] == startColor) {
                        toBeFilled.add(down);
                    }
                    // up
                    int up = current - column;
                    if (current / column > 0 && !filled.contains(up) && image[up / column][up % column] == startColor) {
                        toBeFilled.add(up);
                    }
                }
            }
        }
        return image;
    }
}