// similar to quick sort. find the position and iterate until the position is k - 1.
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return nums[findKthLargestHelper(nums, k, 0, nums.length - 1)];
    }
    
    private int findKthLargestHelper(int[] nums, int k, int l, int r) {
        if (l >= r) return l;
        int position = position(nums, l, r);
        if (position == k - 1) {
            return position;
        } else if (position < k - 1) {
            return findKthLargestHelper(nums, k, position + 1, r);
        } else {
            return findKthLargestHelper(nums, k, l, position - 1);
        }
    }
    
    private int position(int[]nums, int l, int r) {
        int left = l;
        int right = r + 1;
        while (left < right) {
            while (nums[++left] > nums[l]) {
                if (left == r) {
                    break;
                }
            }
            while (nums[--right] < nums[l]) {
                if (right == l) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            swap(nums, left, right);
        }
        swap(nums, l, right);
        return right;    
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Heap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
            new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
          heap.add(n);
          if (heap.size() > k)
            heap.poll();
        }

        // output
        return heap.poll();        
  }
}

// Built-in Sort
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
