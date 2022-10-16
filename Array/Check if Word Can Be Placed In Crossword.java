/*Intuition: Two pointers approach -> Once we find a valid starting space(an open cell or matching character to the beginning of our word), 
 we can look to ensure it does not break rule where the opposite of the direction we are going should be a wall ('#' or out of bounds). 
 If rules 3/4 are not broken, we simply apply the typical two pointer approach moving the pointer in the word forward while simultaneously moving the row/col pointers forward in the appropriate direction (breaking early if we find an invalid cell or mismatching character).
 If we reach the end of the word and the row/col pointers are on 
 a wall ('#' or out of bounds), we have found a valid place to place our word.
*/
Implementation:

public boolean placeWordInCrossword(char[][] board, String word) {
    int n = board.length, m = board[0].length, dirs[][] = new int[][]{ {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (board[i][j] == ' ' || board[i][j] == word.charAt(0)) { // valid starting space
                for (int[] dir : dirs) { // try each direction
                    int r = i, c = j, idx = 0;
                    if (in(board, r - dir[0], c - dir[1]) && board[r - dir[0]][c - dir[1]] != '#') continue; // invalid condition
                    
                    while (idx < word.length() && in(board, r, c)) {
                        if (board[r][c] == '#' || (board[r][c] != ' ' && board[r][c] != word.charAt(idx))) break;
                        idx++;
                        r += dir[0];
                        c += dir[1];
                    }

                    if (idx == word.length() && (!in(board, r, c) || board[r][c] == '#')) return true;
                }
            }
        }
    }
    
    return false;
}

public boolean in(char[][] board, int r, int c) {
    return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
}