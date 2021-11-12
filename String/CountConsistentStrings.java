class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int consistentCount = words.length;
        Set<Character> allowedSet = new HashSet();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!allowedSet.contains(c)) {
                    consistentCount--;
                    break;
                }
            }
        }
        return consistentCount;
    }
    
}