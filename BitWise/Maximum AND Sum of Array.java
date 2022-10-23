// bitmask to represent the state of each slot
class Solution {
  private int numSlots;
  private int[] nums;
  private int[][] memo;

  public int maximumANDSum(int[] nums, int numSlots) {
    int state = (1 << (numSlots * 2)) - 1;
    this.numSlots = numSlots;
    this.nums = nums;
    this.memo = new int[nums.length][state + 1];

    return dp(0, state);
  }

  private int dp(int i, int state) {
    if (i == nums.length) return 0;
    if (memo[i][state] != 0) return memo[i][state];

    int res = Integer.MIN_VALUE >> 1;
    for (int j = 0; j < numSlots; j++) {
      // use 2 bits to represent the state of each slot
      int slot = state >> (j * 2) & 3;
      if (slot == 0) continue;
     
      // 3 -> 1 -> 0
      // 
      // 11 (3 - 2) => 1
      // 01 (1 - 1) => 0
      // 00
      res = Math.max(res, dp(i + 1, state - ((slot + 1) / 2 << (j * 2))) + (nums[i] & (j + 1)));
    }

    return memo[i][state] = res;
  }
}


// dfs + memorization
class Solution {
  private Map<String, Integer> memo = new HashMap<>();
  
  public int maximumANDSum(int[] nums, int numSlots) {
    int[] slots = new int[numSlots + 1];
    for (int i = 1; i <= numSlots; i++) {
      slots[i] = 2;
    }
    return dfs(nums, slots, 0);
  }

  private int dfs(int[] nums, int[] slots, int i) {
    if (i == nums.length) return 0;
    String key = Arrays.toString(slots) + "," + i;
    if (memo.containsKey(key)) return memo.get(key);
    
    int ans = Integer.MIN_VALUE >> 1;
    for (int j = 1; j < slots.length; j++) {
      if (slots[j] == 0) continue;

      slots[j]--;
      ans = Math.max(ans, dfs(nums, slots, i + 1) + (nums[i] & j));
      slots[j]++;
    }
    
    memo.put(key, ans);
    return ans;
  }
}