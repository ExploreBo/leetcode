// two pointers. Time Complexity: O(N)
class Solution {
    public int longestMountain(int[] A) {
        int N = A.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }

        return ans;
    }
}

// brute force: get each real peak and get the mountain size. return the max
class Solution {
    public int longestMountain(int[] arr) {
        if (arr.length < 3) return 0;
        int max = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int curr = 3;
                int l = i - 1;
                int r = i + 1;
                while (l > 0) {
                    if (arr[l] > arr[l - 1]) {
                        curr++;
                        l--;
                    } else {
                        break;
                    }
                }
                while (r < arr.length - 1) {
                    if (arr[r] > arr[r + 1]) {
                        curr++;
                        r++;
                    } else {
                        break;
                    }
                }                
                max = Math.max(curr, max);
            }
        }
        return max;
    }
}
