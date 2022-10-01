class Solution {
    public boolean hasAlternatingBits(int n) {
        int cur = n & 1;
        n = n >> 1;
        while (n > 0) {
            if (cur == (n & 1)) return false;
            cur = n & 1;
            n = n >> 1;
        }
        return true;
    }
}


   boolean hasAlternatingBits2(int n) {
        /*
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */

        n = n ^ (n>>1);
        return (n & n+1) == 0;
    }