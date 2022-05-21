class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int result = 0;
        int currMinPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - currMinPrice);
            currMinPrice = Math.min(currMinPrice, prices[i]);
        }
        return result;
    }
}