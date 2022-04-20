// Solution 1: extend the matrix to a 1D array. Time complexity: O(log(m*n))
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0; 
        int r = m * n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            }
            if (matrix[mid / n][mid % n] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return matrix[l / n][l % n] == target;
    }
}

/* Solution 2: 2 search, first get the target row, then search in the row.
   Time complexity: O(log(m) + O(log(n))
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row = findRow(matrix, target, 0, m - 1);
        
        if (row == -1) {
            return false;
        }

        int l = 0; 
        int r = n - 1;        
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }        
        return matrix[row][l] == target;
    }
    
    private int findRow(int[][] matrix, int target, int l, int r) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid][0] <= target && matrix[mid][matrix[0].length - 1] >= target) {
                return mid;
            }
            if (matrix[mid][0] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}