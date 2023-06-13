// using array
public class Solution {
    public int longestPalindrome(String s) {
        boolean[] map = new boolean[128];
        int len = 0;
        for (char c : s.toCharArray()) {
            map[c] = !map[c];
            if (!map[c]) len+=2;
        }
        if (len < s.length()) len++; // odd
        return len;
    }
}

// using hashmap
class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap();
        int result = 0;
        for (char c : s.toCharArray()) {
            if (map.getOrDefault(c, 0) == 1) {
                result += 2;
                map.put(c, 0);
            } else {
                map.put(c, 1);
            }
        }
        if (result < s.length()) {
            return result + 1;
        } else {
            return result;
        }
    }
}