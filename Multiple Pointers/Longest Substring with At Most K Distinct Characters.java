class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // the frequency of characters in the current window
        Map<Character, Integer> map = new HashMap();
        int l = 0;
        int r = 0;
        int maxLength = 0;
        while (r < s.length()) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            if (map.size() <= k) {
                maxLength = Math.max(maxLength, r - l + 1);
            }
            while (map.size() > k && l <= r) {
                if (map.get(s.charAt(l)) == 1) {
                    map.remove(s.charAt(l));
                } else {
                    map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                }
                l++;
            }
            r++;
        }
        return maxLength;
    }
}
