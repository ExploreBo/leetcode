class Solution {
    public int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        boolean[] b = new boolean[c.length];
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < c.length; i++) {
            if (map.containsKey(c[i])) {
                b[i] = false;
                b[map.get(c[i])] = false;
            } else {
                map.put(c[i], i);
                b[i] = true;
            }
        }

        for (int i = 0; i < c.length; i++) {
            if (b[i] == true) {
                return i;
            }
        }        
        return -1;
    }

    // solution 2
    public int firstUniqChar(String s) {
     int[] charCount = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            charCount[c - 'a']++;   
        }
        for (int i = 0; i < chars.length; i++) {
            if (charCount[chars[i] - 'a'] == 1) {
                return i;
            }    
        }
        return -1;
    }    
}