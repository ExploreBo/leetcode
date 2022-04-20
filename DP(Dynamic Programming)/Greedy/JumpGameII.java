/*
Maintain the farthest(Greedy) we could reach and the current jumpEnd.
If reaches the jump end, make another jump.
*/
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
                // if (currentJumpEnd >= nums.length - 1) {
                //     return jumps;
                // }
            }
        }
        return jumps;
    }
}