// HashSet  O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        int max = 1;
        for (int num : nums) {
            int count = 1;
            if (set.contains(num + 1) && !set.contains(num - 1)) {
                set.remove(num);
                while (set.contains(num + 1)) {
                    count++;
                    set.remove(num + 1);
                    num++;
                }                
            }
            max = Math.max(count, max); 
        }
        return max;
    }
}

// Sorting O(nlogn)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}