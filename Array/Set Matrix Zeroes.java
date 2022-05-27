// Space: O(m + n)
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == true || col[j] == true) {
                    matrix[i][j] = 0;
                }
            }
        }       
    }
}

/* Constant Space. Flag the row and col to be set to 0 with the first cell of that row or col.
   Pay special attention of the first row and col though because it might cause over setting.
   For example,
        [[1,2,3,4],
         [5,0,7,8],
         [0,10,11,12],
         [13,14,15,0]]
    The 3rd element of first row would be the only left '1'. But if you don't handle the special case, it would all be set as '0'.
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstCol = false;
        boolean firstRow = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstRow = true;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstCol = true;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
               
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow == true) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }            
        }
        if (firstCol == true) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }            
        }         
    }
}