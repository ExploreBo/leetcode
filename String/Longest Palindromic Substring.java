/*
Solution: Expand Around Center
Time complexity: O(n^2)
*/
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}

/*
DP Solution
*/
public class Solution {

    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        //Dp array for substring palindrome
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j >= 0; --j) {
                boolean startEqEnd = s.charAt(j) == s.charAt(i);

                if (i == j) {
                    //If the same char: 'a' is palindrome
                    dp[i][j] = true;
                } else if (i - j == 1) {
                    //If length 2: 'ab' is palindrome when 'a' == 'b'
                    dp[i][j] = startEqEnd;
                } else if (startEqEnd && dp[i - 1][j + 1]) {
                    //Otherwise: string is palindrome if s(i) == s(j) and substring s(j + 1, i - 1) is palindrome
                    dp[i][j] = true;
                }

                if (dp[i][j] && i - j > end - start ) {
                    end = i;
                    start = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}