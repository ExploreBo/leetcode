class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] map = new String[26];
        String[] sArray = s.split(" ");
        Set<String> set = new HashSet<>();
        if (sArray.length != pattern.length()) return false;
        for (int i = 0; i < sArray.length; i++) {
            char c = pattern.charAt(i);
            if (map[c - 'a'] == null) {
                map[c - 'a'] = sArray[i];
                if (set.contains(sArray[i])) {
                    return false;
                }
                set.add(sArray[i]);
            } else {
                if (!map[c - 'a'].equals(sArray[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}