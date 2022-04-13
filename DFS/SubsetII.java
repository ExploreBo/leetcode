public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        // [] is always a subset of any set
        result.add(solution);
        if (nums.length == 0) return result;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        helper(nums, used, solution, result, 0);
        return result;
    }
    
    private void helper (int[] nums, boolean[] used, List<Integer> solution, List<List<Integer>> result, int start){
        for (int i = start; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            solution.add(nums[i]);
            used[i] = true;
            // every step add the solution as a subset
            result.add(new ArrayList<Integer>(solution));
            if (start == nums.length){
                return;
            }
            else{
                // start from i + 1
                helper(nums, used, solution, result, i + 1);
                solution.remove(solution.size() - 1);
                used[i] = false;
            }
        }
    }
}