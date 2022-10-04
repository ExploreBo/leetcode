// brute force update: O(1) sumRange: O(n)
private int[] nums;
public int sumRange(int i, int j) {
    int sum = 0;
    for (int l = i; l <= j; l++) {
        sum += data[l];
    }
    return sum;
}

public int update(int i, int val) {
    nums[i] = val;
}
// Time Limit Exceeded


// Segment Tree. update/sumRange O(logn), build: O(n)
class NumArray {
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[2 * n];
        buildTree(nums);
    }
    
    private void buildTree(int[] nums) {
        // initialize the leaf nodes
        for (int i = n, j = 0;  i < 2 * n; i++,  j++) {
            tree[i] = nums[j];
        }
        // initialize the non-leaf nodes
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
    
    public void update(int index, int val) {
        index = index + n; // the leaf node store val
        tree[index] = val;
        while (index > 0) {
            int left = index;
            int right = index;
            if (index % 2 == 0) {
                right = index + 1;
            } else {
                left = index - 1;
            }
            tree[index / 2] = tree[left] + tree[right];
            index /= 2; 
        }
    }
    
    public int sumRange(int left, int right) {
        left += n;
        right += n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */