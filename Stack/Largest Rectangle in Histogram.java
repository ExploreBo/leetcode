// brute force. O(n^2)
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
}

// Divide and Conquer. Average: O(nlogn)
public class Solution {
    public int calculateArea(int[] heights, int start, int end) {
        if (start > end)
            return 0;
        int minindex = start;
        for (int i = start; i <= end; i++)
            if (heights[minindex] > heights[i])
                minindex = i;
        return Math.max(heights[minindex] * (end - start + 1),
                        Math.max(calculateArea(heights, start, minindex - 1),
                                calculateArea(heights, minindex + 1, end))
                );
    }

    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }
}



// 3 pass. see what the rectangle could be if we use heights[i] and get the max.
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] indexFromLeft = new int[len];
        int[] indexFromRight = new int[len];
        int max = 0;
        
        for (int i = 0; i < len; i++) {
            int index = i;
            while (index - 1 >= 0 && heights[index - 1] >= heights[i]) {
                index--;
            }
            indexFromLeft[i] = index;
        }
        
        for (int i = len - 1; i >= 0; i--) {
            int index = i;
            while (index + 1 < len && heights[index + 1] >= heights[i]) {
                index++;
            }
            indexFromRight[i] = index;
        }        
        
        for (int i = 0; i < len; i++) {
            max = Math.max(max, heights[i] * (indexFromRight[i] - indexFromLeft[i] + 1));
        }        
        return max;
    }
}

// using Stack to decrease to 1 pass.
public class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }
}