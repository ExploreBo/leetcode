// 找准确值
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}

// 找模糊值, 找大于target的最小数
public static int findMaxSmallerThanTarget(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
        int mid = (start + end) / 2;
        if (nums[mid] < target) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    return nums[start];
}

// 找模糊值, 找小于target的最大数
public static int findMaxLessThanTarget(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
        int mid = (start + end + 1) / 2;
        if (nums[mid] > target) {
            end = mid - 1;
        } else {
            start = mid;
        }
    }
    return nums[start];
}

// 找模糊值, 找最接近target的数
public static int findClosest(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end - 1) {
        int mid = (start + end) / 2;
        if (nums[mid] > target) {
            end = mid;
        } else {
            start = mid;
        }
    }
    return Math.abs(nums[start] - target) < Math.abs(nums[end] - target) ? nums[start] : nums[end];

}

