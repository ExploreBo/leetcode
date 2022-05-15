class Solution {
    Set<List<Integer>> lists;
    public List<List<Integer>> findSubsequences(int[] nums) {
        lists = new HashSet<>();
        recursion(nums, 0, new ArrayList());
        
        return new ArrayList(lists);
    }
    void recursion(int[] nums, int curr, List<Integer> temp) {
        if (temp.size() >= 2)
            lists.add(new ArrayList(temp));
            
        for (int i = curr; i < nums.length; i++) {
            if (temp.size()==0 || temp.get(temp.size() - 1) <= nums[i]) {
                temp.add(nums[i]);
                recursion(nums, i + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}


public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(new LinkedList<Integer>(), 0, nums, res);
        return res; 
    }

    private void helper(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res) {
        if (list.size() > 1) res.add(new LinkedList<Integer>(list));
        Set<Integer> used = new HashSet<>();
        for(int i = index; i < nums.length; i++){
            if (used.contains(nums[i])) continue;
            if (list.size() == 0 || nums[i] >=list.peekLast()){
                used.add(nums[i]);
                list.add(nums[i]); 
                helper(list, i + 1, nums, res);
                list.remove(list.size() - 1);
            }
        }
    }
}