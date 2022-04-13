class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n < k) return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> solution = new ArrayList<Integer>();
        combineUtil(result, solution, n, k, 1);
        return result;
    }
    private void combineUtil(List<List<Integer>> result, List<Integer> solution, int n, int k, int start){
        if (solution.size() == k){
            result.add(new ArrayList<Integer>(solution));
            return;
        }
        for(int i= start; i<= n; i++){
            solution.add(i);
            combineUtil(result, solution, n, k, i + 1);
            solution.remove(solution.size() - 1);
        }
    }   
}