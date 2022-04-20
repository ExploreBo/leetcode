class Solution {
    public int mySqrt(int x) {
        if (x <= 1)
            return 0;
        int left = 1, right = x;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x /(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
}


// Solution 2
class Solution {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int l = 1, r = x;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (mid == x / mid) {
                return mid;
            }
            else if (x / mid > mid) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return l;
    }
}