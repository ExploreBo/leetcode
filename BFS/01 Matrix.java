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


// DP
public int[][] updateMatrix(int[][] matrix) {
    int[][] ret = new int[matrix.length][matrix[0].length];
    int[][] dirs = new int[][]{{-1,0}, {0,-1}};

    // first step iteration
    for(int i=0; i<matrix.length; i++) {
        for(int j=0; j<matrix[0].length; j++) {
            if (matrix[i][j]==0) {
                ret[i][j]=0;
            } else {
                ret[i][j] = matrix.length*matrix[0].length;
                for(int k=0; k<2; k++){
                    int x = i+dirs[k][0];
                    int y = j+dirs[k][1];

                    // check if neighbour is valid
                    if (x<0 || y<0){
                        continue;
                    }
                    ret[i][j] = Math.min(ret[i][j],ret[x][y]);
                }
                ret[i][j]++;
            }
        }
    }

    // second step iteration in reverse direction
    for(int i=matrix.length-1; i>=0; i--){
        for(int j=matrix[0].length-1; j>=0; j--){
            if (matrix[i][j]==0) {
                ret[i][j]=0;
            } else {
                for (int k=0; k<2; k++) {
                    int x = i-dirs[k][0];
                    int y = j-dirs[k][1];

                    // check if neighbour is valid
                    if (x >= matrix.length || y >= matrix[0].length) {
                        continue;
                    }
                    ret[i][j] = Math.min(ret[i][j],ret[x][y]+1);
                }
            }
        }
    }
    return ret;
}
