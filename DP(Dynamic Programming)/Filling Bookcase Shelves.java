// TLE
class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return getMinHeight(books, shelfWidth, 0, 0, 0, 0);
    }

    public int getMinHeight(int[][] books, int shelfWidth, int currentWidth, int currentHeight, int preHeight, int index) {
        if (index == books.length) return preHeight + currentHeight;
        if (index == 0) {
            return getMinHeight(books, shelfWidth, books[0][0], books[0][1], 0, 1);
        }
        int minHeight = Integer.MAX_VALUE;
        if (currentWidth + books[index][0] <= shelfWidth) {
            minHeight = getMinHeight(books, shelfWidth, currentWidth + books[index][0], Math.max(currentHeight, books[index][1]), preHeight, index + 1);
        }
        return Math.min(minHeight, getMinHeight(books, shelfWidth, books[index][0], books[index][1], preHeight + currentHeight, index + 1 ));
    }
}

// memorization
// the position m[i][w] contains the minimum height for books [i...n], so we do not need to care what the current height is.
class Solution {
    private int[][] m = new int[1001][1001];
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return minHeightShelves(books, shelfWidth, 0, 0, 0);
    }
    
    private int minHeightShelves(int[][] books, int maxWidth, int idx, int w, int h) {
        if (idx >= books.length) return h;
        if (m[idx][w] != 0) return m[idx][w];
        return m[idx][w] = Math.min(
            // min of placing book in new shelf, updating latest h and w of current book
            h + minHeightShelves(books, maxWidth, idx+1, books[idx][0], books[idx][1]),
            // or placing book in same shelf and check min h
            w + books[idx][0] > maxWidth ? Integer.MAX_VALUE : minHeightShelves(books, maxWidth, idx+1, w + books[idx][0], Math.max(h, books[idx][1]))
        );
    }

}

// DP
/*
For each book, we first put it on a new level of the shelf which is the least preferable way to do, 
in this case the current height will be dp[i - 1] + books[i- 1][1]. 
Then, we check back previously put books and see if it is possible to get better arrangement(less height) 
by putting the current book together with the books at previous level of the shelf. If better arrangement is possible, 
dp[i] will be updated. The inner loop will terminate once accumulated width exceeds the bookshelf's width.

*/
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + books[i - 1][1];
            int height = books[i - 1][1];
            int width = books[i - 1][0];
            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; j--) {
                height = Math.max(height, books[j - 1][1]);
                width += books[j - 1][0];
                dp[i] = Math.min(dp[i], height + dp[j - 1]);
            }
        }
        return dp[n];
    }