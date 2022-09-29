class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += getCountOfPalinDromic(s, i, i);
            count += getCountOfPalinDromic(s, i, i + 1);
        }
        return count;
    }

    private int getCountOfPalinDromic(String s, int lo, int hi) {
        int count = 0;
        while (lo >= 0 && hi < s.length()) {
            if (s.charAt(lo) == s.charAt(hi)) {
                count++;
                lo--;
                hi++;
            } else {
                return count;
            }
        }
        return count;
    }
}
