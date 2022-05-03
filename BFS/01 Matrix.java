// BFS
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int[][] distance = new int[mat.length][mat[0].length];
        Queue<int[]> queue = new LinkedList();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                } else {
                    distance[i][j] = Integer.MAX_VALUE - 1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.remove();
                int currDis = distance[curr[0]][curr[1]];
                if (curr[0] + 1 < mat.length && distance[curr[0] + 1][curr[1]] > currDis + 1) {
                    distance[curr[0] + 1][curr[1]] = currDis + 1;
                    queue.add(new int[]{curr[0] + 1, curr[1]});
                } 
                if (curr[0] - 1 >= 0 &&distance[curr[0] - 1][curr[1]] > currDis + 1) {
                    distance[curr[0] - 1][curr[1]] = currDis + 1;
                    queue.add(new int[]{curr[0] - 1, curr[1]});                    
                }
                if (curr[1] + 1 < mat[0].length && distance[curr[0]][curr[1] + 1] > currDis + 1) {
                    distance[curr[0]][curr[1] + 1] = currDis + 1;
                    queue.add(new int[]{curr[0], curr[1] + 1});                    
                }
                if (curr[1] - 1 >= 0 && distance[curr[0]][curr[1] - 1] > currDis + 1) {
                    distance[curr[0]][curr[1] - 1] = currDis + 1;
                    queue.add(new int[]{curr[0], curr[1] - 1});                     
                }          
            }
        }
        return distance;
    }
}

// DP: two passes. One is from top-left to bottom-right, the other is from bottom-left to top-right.
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int[][] distance = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0;
                } else {
                    // cannot use MAX_VALUE to avoid overflow
                    distance[i][j] = Integer.MAX_VALUE - 1;
                }
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i > 0) {
                    distance[i][j] = Math.min(distance[i][j], distance[i - 1][j] + 1);
                }
                if (j > 0) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][j - 1] + 1);
                }
            }
        }
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (i < mat.length - 1) {
                    distance[i][j] = Math.min(distance[i][j], distance[i + 1][j] + 1);
                }
                if (j < mat[0].length - 1) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][j + 1] + 1);
                }
            }
        }        
        return distance;
    }
}