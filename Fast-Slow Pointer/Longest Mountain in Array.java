// two pointers. Time Complexity: O(N)
class Solution {
    public int longestMountain(int[] arr) {
        int max = 0;
        int start = 0;
        int n = arr.length;
        while (start < arr.length) {
            int end = start;
            if (end + 1 < n && arr[end + 1] > arr[end]) {
                while (end + 1 < n && arr[end + 1] > arr[end]) {
                    end++;
                }
                if (end + 1 < n && arr[end + 1] < arr[end]) {
                    while (end + 1 < n && arr[end + 1] < arr[end]) {
                        end++;
                    }
                    // max will be updated only if the mountain exist
                    max = Math.max(max, end - start + 1);
                }
            }
            // the start should be updated every time to avoid infinite loop
            start = Math.max(start + 1, end);
        }
        return max;
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
