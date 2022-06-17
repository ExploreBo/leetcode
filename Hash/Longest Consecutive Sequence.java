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