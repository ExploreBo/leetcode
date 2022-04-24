// insipired by https://leetcode.com/problems/longest-mountain-in-array/
// difference is how we deal with the end. 
class Solution {
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int n = height.length;
        int start = 0;
        int result = 0;
        while (start < n) {
            int end = start;
            if (end + 1 < n && height[end + 1] < height[end]) {
                while (end + 1 < n && height[end + 1] <= height[end]) {
                    end++;
                }
                if (end + 1 < n && height[end + 1] > height[end]) {
                    int index = ++end;
                    while (index < n) {
                        if (height[index] > height[start]) {
                            end = index;
                            break;
                        }
                        if (height[index] >= height[end]) {
                            end = index;
                        }
                        index++;
                    }
                    int k = start + 1;
                    while (k < end) {
                        result = result + Math.max(Math.min(height[start], height[end]) - height[k], 0);
                        k++;
                    }
                }
            }
            start = Math.max(end, start + 1);
        }
        return result;
    }
}

// two pointers
// start from the smaller side
class Solution {
    public int trap(int[] height) {
        int result = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            if (height[start] <= height[end]) {
                int current = height[start];
                while (height[++start] < current) {
                    result += current - height[start];
                }
            } else {
                int current = height[end];
                while(height[--end] < current) {
                    result += current - height[end];
                }
            }
        }
        return result;
    }
}

// Use DP to find the highest bar size upto that index.
class Solution {
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i > 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            result += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        
        return result;
    }
}


