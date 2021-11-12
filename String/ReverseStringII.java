class Solution {
    public void reverseString(char[] s, int l, int k) {
        int r = Math.min(s.length, k) - 1;
        while (l < r) {
            char temp = s[l];
            s[l++] = s[r];
            s[r--] = temp;
        }
    }
    public String reverseStr(String s, int k) {
       char [] c = s.toCharArray();
        for (int i = 0; i < c.length; i += 2 * k) {
           reverseString(c, i, i+k);
        }
        return String.valueOf(c);
    }
}