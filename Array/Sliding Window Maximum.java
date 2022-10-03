/*
Brute Force: time complexity O(nk)
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length; 
        int[] result = new int[n - k + 1];
        for (int i = 0; i + k - 1 < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(nums[i + j], max);
            }
            result[i] = max;
        }
        return result;
    }
}

/*
Time complexity: O(n)
Basically maintain a decreasing queue that the left most element is always the maximum of current window.
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        int[] result = new int[nums.length - k + 1];
        
        for (int end = 0; end < nums.length; end++) { 
            int cur = nums[end];
            // keep the max always in the head
            while (!deque.isEmpty() && nums[deque.peekLast()] < cur) { 
                deque.removeLast();
            }
            // if the head is not in scope, just remove
            while (!deque.isEmpty() && deque.peekFirst() < end - k + 1) { 
                deque.removeFirst();
            }
            deque.addLast(end);
            
            if (end + 1 >= k) {
                result[index++] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
}

/*
Time complexity: O(n)
Split array into blocks and in each block, track the max from left to right and from right to left.
Use two arrays to store the temp max numbers.
This make sure every time the window slides, we can always get the max from either the head or the tail.s
*/
class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n * k == 0) return new int[0];
    if (k == 1) return nums;

    int [] left = new int[n];
    left[0] = nums[0];
    int [] right = new int[n];
    right[n - 1] = nums[n - 1];
    for (int i = 1; i < n; i++) {
      // from left to right
      if (i % k == 0) left[i] = nums[i];  // block_start
      else left[i] = Math.max(left[i - 1], nums[i]);

      // from right to left
      int j = n - i - 1;
      if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
      else right[j] = Math.max(right[j + 1], nums[j]);
    }

    int [] output = new int[n - k + 1];
    for (int i = 0; i < n - k + 1; i++)
      output[i] = Math.max(left[i + k - 1], right[i]);

    return output;
  }
}