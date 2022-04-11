class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // sort is necessary, we will iterate from the minimum. Otherwise, we will miss a few answers
    	Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);
        
        return result;
    }
    
    private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
        if (target > 0) {
            // target >= candidates[i] could eliminate unnecessary loops
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                cur.add(candidates[i]);
                getResult(result, cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);                     
            }

        } else if (target == 0) {
            // we need to add the copy of cur instead of cur itself
            result.add(new ArrayList<Integer>(cur));
        }
    } 
}