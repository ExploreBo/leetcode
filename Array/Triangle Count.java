/*
sort and set the largest edge and use two pointers to find pairs of the rest.
it is hard to set the shortest edge and get the rest two.
*/
public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        // write your code here
        if(S == null || S.length < 3){
            return 0;
        }

        Arrays.sort(S);
        int count = 0;

        for(int i = 2; i < S.length; i++){
            count += getTriangleCount(S, i);
        }

        return count;
    }
    
    public int getTriangleCount(int[] S, int position){
        int left = 0, right = position - 1;
        int target = S[position];
        int count = 0;

        while(left < right){
            if(S[left] + S[right] > target){
                count += right - left;
                right--;
            }else{
                left++;
            }
        }
        
        return count;
    }
}