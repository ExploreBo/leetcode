/*
When max == i, means all the numbers smaller than the current index are on the left.
Then we can add a chunk.
*/
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr.length < 2) return arr.length;
        int max = 0;
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ++count;
            }
        }
        return count;
    }
}
