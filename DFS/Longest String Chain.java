// O(L^2 * N)
class Solution {

    private int dfs(Set<String> words, Map<String, Integer> memo, String currentWord) {
        // If the word is encountered previously we just return its value present in the map (memoization).
        if (memo.containsKey(currentWord)) {
            return memo.get(currentWord);
        }
        // This stores the maximum length of word sequence possible with the 'currentWord' as the
        int maxLength = 1;
        StringBuilder sb = new StringBuilder(currentWord);

        // creating all possible strings taking out one character at a time from the `currentWord`
        for (int i = 0; i < currentWord.length(); i++) {
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            // If the new word formed is present in the list, we do a dfs search with this newWord.
            if (words.contains(newWord)) {
                int currentLength = 1 + dfs(words, memo, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }
            sb.insert(i, currentWord.charAt(i));
        }
        memo.put(currentWord, maxLength);

        return maxLength;
    }

    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> wordsPresent = new HashSet<>();
        Collections.addAll(wordsPresent, words);
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, dfs(wordsPresent, memo, word));
        }
        return ans;
    }
}


// My TLE(Time Limit Exceeded) version
// O(N ^ 2 * L)
class Solution {
    int maxCount = 1;
    public int longestStrChain(String[] words) {
        int longestChainLength = 1;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, List<Node>> map = new HashMap();
        Map<Integer, List<Node>> predecessorsMap = new HashMap();
        for (String word : words) {
            int length = word.length();
            min = Math.min(min, length);
            max = Math.max(max, length);
            map.putIfAbsent(length, new ArrayList());
            map.get(length).add(new Node(word));
        }
        predecessorsMap.put(min, map.get(min));
        List<Node> prePredecessors = null;
        while (min < max && map.containsKey(min)) {
            List<Node> nodes = map.get(min);
            for (Node node : nodes) {
                dfs(node.word, map, 1);
            }
            min++;
        }
        return this.maxCount;
    }

    private void dfs(String word, Map<Integer, List<Node>> map, int count) {
        List<Node> candidates = map.get(word.length() + 1);
        if (candidates == null) return;
        for (Node candidateNode : candidates) {
            if (isPredecessor(word, candidateNode.word)) {
                this.maxCount = Math.max(count + 1, this.maxCount);
                dfs(candidateNode.word, map, count + 1);
            }
        }

    }

    private boolean isPredecessor(String origin, String predecessor) {
        if (origin.length() + 1 != predecessor.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < origin.length()) {
            if (origin.charAt(i) == predecessor.charAt(j)) {
                ++i;
                ++j;
            } else {
                ++j;
            }
            if (j > i + 1) {
                return false;
            }
        }
        return true;
    }
}

class Node {
    String word;
    List<Node> predecessors;

    Node(final String word) {
        this.word = word;
        predecessors = new ArrayList();
    }
}