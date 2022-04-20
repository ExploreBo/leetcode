/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        int l = 0;
        int r = 1;
        // find the correct right boundary in logarithmic time
        while (reader.get(r) <= target) {
            // bit operation is faster than multiple operation
            r <<= 1;
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (reader.get(mid) == target) {
                return mid;
            }
            if (reader.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return reader.get(l) == target ? l : -1;
    }
}