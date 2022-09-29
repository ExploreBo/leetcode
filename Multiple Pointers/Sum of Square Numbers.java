class Solution {
    public boolean judgeSquareSum(int c) {
        int lo = 0;
        int hi = (int) (Math.sqrt(c) + 1);
        while (lo <= hi) {
            int diff = c - lo * lo - hi * hi;
            if (diff == 0) {
                return true;
            } else if (diff > 0) {
                lo++;
            } else {
                hi--;
            }
        }
        return false;
    }
}