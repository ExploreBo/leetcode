class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        premuteUtil(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void premuteUtil(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                // !used[i - 1] only eliminates the consecutive case, like 1,2,2,3
                // but it won't eliminate 1,2,3,2
                if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true; 
                tempList.add(nums[i]);
                premuteUtil(list, tempList, nums, used);
                used[i] = false; 
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}