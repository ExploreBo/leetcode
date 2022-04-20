/*
Solution: Brute Force, try from 1, until succeed.
*/
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int k = 1;
        while (true) {
            int count = 0;
            for (int i = 0; i < piles.length; i++) {
                count += (piles[i] + k - 1) / k;
                if (count > h) {
                    break;
                }
            }
            if (count <= h) {
                return k;
            }
            k++;
        }
    }
}


/*
Solution: We know a range for trying that is [1, the largest count in the pile].
So we try from the range in binary search way.
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
                count += (piles[i] + k - 1) / k;
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
