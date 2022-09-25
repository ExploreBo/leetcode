// brute force
class Solution {
    public boolean differByOne(String[] dict) {
        int stringLength = dict[0].length();
        for (int i = 0; i < dict.length - 1; i++) {
            for (int j = i + 1; j < dict.length; j++) {
                // compare dict[i] and dict[j]
                int differCount = 0;
                for (int k = 0; k < stringLength; k++) {
                    if (dict[i].charAt(k) != dict[j].charAt(k)) {
                        differCount++;
                    }
                }
                if (differCount == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

// using hashSet
class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String d : dict) {
            for (int i = 0; i < d.length(); i++) {
                StringBuilder sb = new StringBuilder(d);
                sb.setCharAt(i, '*');
                String candidate = sb.toString();
                if (!set.add(candidate)) return true;
            }
        }
        return false;
    }
}


// Rabin Karp Algorithm. Just like numbers, we make the string into a number, 26 based.
class Solution {
    public boolean differByOne(String[] dict) {
        HashSet<Long> set = new HashSet<>();
        long mod = (long) Math.pow(10, 20) + 7;

        int len = dict[0].length();
        long[] word2hash = new long[dict.length];
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < len; j++) {
                word2hash[i] = (word2hash[i] * 26 + dict[i].charAt(j) - 'a') % mod;
            }
        }

        long base = 1;
        for (int j = len - 1; j >= 0; j--) {
            set.clear();
            for (int i = 0; i < dict.length; i++) {
                long newHash = (word2hash[i] - base * (dict[i].charAt(j) - 'a')) % mod;
                if (set.contains(newHash)) {
                    return true;
                }
                set.add(newHash);
            }
            base = 26 * base % mod;
        }

        return false;
    }
}