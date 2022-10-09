class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // sort is necessary, we will iterate from the minimum.
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
                getResult(result, cur, candidates, target - candidates[i], i + 1);
                cur.remove(cur.size() - 1);
                // this is to eliminate the duplicate values in the candidates
                while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                    i++;
                }
            }

        } else if (target == 0) {
            // we need to add the copy of cur instead of cur itself
            result.add(new ArrayList<Integer>(cur));
        }
    } 
}