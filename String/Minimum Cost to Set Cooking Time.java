class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int m = targetSeconds / 60, s = targetSeconds % 60;
        return Math.min(getCost(startAt, m, s, moveCost, pushCost), getCost(startAt, m-1, s+60, moveCost, pushCost));
    }
    
    private int getCost(int prev, int m, int s, int moveCost, int pushCost) {
        if (Math.min(m, s) < 0 || Math.max(m, s) > 99) return Integer.MAX_VALUE;
        int res = 0;
        for (char digit : String.valueOf(m*100 + s).toCharArray()) {
            res += (pushCost + (prev == digit - '0' ? 0 : moveCost));
            prev = digit - '0';
        }
        return res;
    }
}
