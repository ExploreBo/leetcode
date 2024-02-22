// Memeroization
// O(ns), s is the amount, s is the number of coins
class Solution {
    private Map<Integer, Integer> map;
    public int coinChange(int[] coins, int amount) {
        map = new HashMap();
        return coinChange(coins, amount, map);
    }

    public int coinChange(int[] coins, int amount, Map<Integer, Integer> map) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        int min = -1;
        for (int i = 0; i < coins.length; ++i) {
            int count = coinChange(coins, amount - coins[i], map);
            if (count >= 0) {
                min = min == -1 ? count + 1 : Math.min(min, count + 1);
            }
        }
        map.put(amount, min);
        return min;
    }    
}


// BFS The problem asks for fewest coins. It is natrual to think of BFS which guarantess shortest path.
// Also O(ns)
	public int coinChange(int[] coins, int amount) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int cs = 0;
        boolean[] vstd = new boolean[amount+1];
        while(!q.isEmpty()) {
            int n = q.size();
            for(int i=0;i<n;i++) {
                int sum = q.poll();
                if(sum==amount) {
                    return cs;
                }
                if(sum>amount || vstd[sum]) {
                    continue;
                }
                vstd[sum]=true;
                for(int coin:coins) {
                    q.add(sum+coin);
                }
            }
            cs++;
        }
        return -1;
    }


// DP top down, F(S)=F(S−C)+1
public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) return 0;
    return coinChange(coins, amount, new int[amount]);
  }

  private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) return -1;
    if (rem == 0) return 0;
    if (count[rem - 1] != 0) return count[rem - 1];
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChange(coins, rem - coin, count);
      if (res >= 0 && res < min)
        min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }
}    

// DP, bottom up 
public class Solution {
  public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }
}