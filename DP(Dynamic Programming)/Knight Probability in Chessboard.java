// recursion. Didn't pass the test cases. Time limit exceeded.
// Time complexity is 8^k

class Solution {
    private double probability;
    private int maxMove;
    Pair<Double, Integer>[][] memory;
    public double knightProbability(int n, int k, int row, int column) {
        maxMove = k;
        probability = 1.0;
        memory = new Pair[n][n];
        if (row < 0 || row >= n || column < 0 || column >= n) return 0.0;
        knightProbabilityHelper(n, 1, row, column);
        return probability;
    }
    private void knightProbabilityHelper(int n, int k, int row, int column) {
        if (k > maxMove) return;
        double probabilityofFailing = 0.0;
        // if (memory[row][column] != null) {
        //     probabilityofFailing = memory[row][column].getKey() / Math.pow(8, k - memory[row][column].getValue());
        //     this.probability = this.probability - probabilityofFailing;
        //     return;
        // }        

        // (row, column) is the current position
        // we have eight choices
        if (row - 2 >= 0 && column + 1 < n) {
            knightProbabilityHelper(n, k + 1, row - 2, column + 1);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k);   
        }
        
        if (row - 1 >= 0 && column + 2 < n) {
            knightProbabilityHelper(n, k + 1, row - 1, column + 2);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        
        if (row + 1 < n && column + 2 < n) {
            knightProbabilityHelper(n, k + 1, row + 1, column + 2);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        
        if (row + 2 < n && column + 1 < n) {
            knightProbabilityHelper(n, k + 1, row + 2, column + 1);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        
        if (row + 2 < n && column - 1 >= 0) {
            knightProbabilityHelper(n, k + 1, row + 2, column - 1);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        
        if (row + 1 < n && column - 2 >= 0) {
            knightProbabilityHelper(n, k + 1, row + 1, column - 2);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k);
        }
        
        if (row - 1 >= 0 && column - 2 >= 0) {
            knightProbabilityHelper(n, k + 1, row - 1, column - 2);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        
        if (row - 2 >= 0 && column - 1 >= 0) {
            knightProbabilityHelper(n, k + 1, row - 2, column - 1);
        } else {
            probabilityofFailing += 1 / Math.pow(8, k); 
        }
        this.probability = this.probability - probabilityofFailing;
        this.memory[row][column] = new Pair<Double, Integer>(probabilityofFailing, k);
        
    }
}


// DP. Time Complexity is O(N^2 * K)
// dp[m][n] means the probability the knight is at this position after certain steps.
// keep updating dp[mn] K times.
class Solution {
    public double knightProbability(int N, int K, int sr, int sc) {
        double[][] dp = new double[N][N];
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        dp[sr][sc] = 1;
        for (; K > 0; K--) {
            double[][] dp2 = new double[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < 8; k++) {
                        int cr = r + dr[k];
                        int cc = c + dc[k];
                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                            dp2[cr][cc] += dp[r][c] / 8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double ans = 0.0;
        for (double[] row: dp) {
            for (double x: row) ans += x;
        }
        return ans;
    }
}