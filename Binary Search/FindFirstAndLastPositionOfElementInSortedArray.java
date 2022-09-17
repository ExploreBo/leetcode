class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {return new int[]{-1, -1};};
        return new int[]{findFirst(nums, target), findLast(nums, target)};
    }
    
    private int findFirst(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            int mid = (start + end) / 2;
            if (nums[mid] < target){
                start = mid + 1;
            } else{
                end = mid;
            }
        }
        return nums[start] == target ? start : -1;
    }

    private int findLast(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            // + 1 to avoid infinite loop
            int mid = (start + end + 1) / 2;
            if (nums[mid] <= target){
                start = mid;
            } else{
                end = mid - 1;
            }
        }
        return nums[start] == target ? start : -1;
    } 
}


// while (start <= end) version
  public static int[] findRange(int[] arr, int key) {
    int[] result = new int[] { -1, -1 };
    result[0] = search(arr, key, false);
    if (result[0] != -1) // no need to search, if 'key' is not present in the input array
      result[1] = search(arr, key, true);
    return result;
  }

  // modified Binary Search
  private static int search(int[] arr, int key, boolean findMaxIndex) {
    int keyIndex = -1;
    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < arr[mid]) {
        end = mid - 1;
      } else if (key > arr[mid]) {
        start = mid + 1;
      } else { // key == arr[mid]
        keyIndex = mid;
        if (findMaxIndex)
          start = mid + 1; // search ahead to find the last index of 'key'
        else
          end = mid - 1; // search behind to find the first index of 'key'
      }
    }
    return keyIndex;
  }