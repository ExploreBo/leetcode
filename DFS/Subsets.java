public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        // [] is always a subset of any set
        result.add(solution);
        if (nums.length == 0) return result;
        Arrays.sort(nums);
        helper(nums, solution, result, 0);
        return result;
    }
    
    private void helper (int[] nums, List<Integer> solution, List<List<Integer>> result, int start){
        for (int i = start; i < nums.length; i++) {
            solution.add(nums[i]);
            // every step add the solution as a subset
            result.add(new ArrayList<Integer>(solution));
            if (start == nums.length) {
                // no need to continue
                return;
            }
            else {
                // start from i + 1
                helper(nums, solution, result, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }
}