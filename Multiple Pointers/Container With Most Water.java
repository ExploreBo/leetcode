// brute force
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int containerHeight = Math.min(height[i], height[j]);
                max = Math.max(max, containerHeight * (j - i));
            }
        }
        return max;
    }
}


// two pointer
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}

// similar with the above solution, just do more iteration to find the next larger height
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                int k = l;
                while (k < r && height[k] <= height[l]) {
                    k++;
                }
                l = k;
                // l++;
            } else {
                int k = r;
                while (k > l && height[k] <= height[r]) {
                    k--;
                }
                r = k;   
                // r--;
            }
        }
        return maxarea;
    }
}