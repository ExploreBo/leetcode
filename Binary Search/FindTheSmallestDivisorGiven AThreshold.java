/*
The same question as: https://leetcode.com/problems/koko-eating-bananas/

*/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // you can either get the max by iterating the array (O(n))
        // or just use the largetst number defined in the constraint
        Arrays.sort(piles);
        int l = 1; 
        int r = piles[piles.length - 1];
        while (l < r) {
            int count = 0;
            int mid = l + (r - l) / 2;
            for (int i = 0; i < piles.length; i++) {
                count += (piles[i] + mid - 1) / mid;
            }  
            if (count <= h) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}