class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < len; j++) {
                if (map.get(words[i].charAt(j)) > map.get(words[i + 1].charAt(j))) {
                    return false;
                } else if (map.get(words[i].charAt(j)) == map.get(words[i + 1].charAt(j))) {
                    if (j == len - 1 && words[i].length() > words[i + 1].length()) {
                        return false;
                    }                    
                    continue;
                } else {
                    break;
                }
            }

        }
        return true;
    }
}