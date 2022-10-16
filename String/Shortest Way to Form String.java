/*
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.


Example:
Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
*/

class Solution {
    public int shortestWay(String source, String target) {
        int indexOfSource = -1;
        int indexOfTarget = 0;
        int count = 0;
        while (indexOfTarget < target.length()) {
            char cur = target.charAt(indexOfTarget);
            boolean startOver = indexOfSource == -1;
            indexOfSource = source.indexOf(cur, indexOfSource + 1);
            if (indexOfSource != - 1) {
                indexOfTarget++;
                if (startOver) {
                    count++;
                }
            } else {
                if (startOver) {
                    return -1;
                }
            }
        }
        return count;
    }
}