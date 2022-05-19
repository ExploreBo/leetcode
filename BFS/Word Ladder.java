class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap();
        int size = wordList.size();
        wordList.add(0, beginWord);
        for (int i = 0; i < wordList.size(); i++) {
            String currStr = wordList.get(i);
            if (currStr.equals(endWord)) {
                map.put(endWord, null);
            } else {
                List<String> neib = new ArrayList();
                for (int j = 1; j < wordList.size(); j++) {
                    if (i != j && diffBySingleLetter(currStr, wordList.get(j))) {
                        neib.add(wordList.get(j));
                    }                                
                }                
                map.put(currStr, neib);
            }
        }
        if (!map.containsKey(endWord)) return 0; 
        Queue<String> queue = new LinkedList();
        Set<String> visited = new HashSet();
        queue.add(beginWord);
        visited.add(beginWord);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String currStr = queue.poll();
                if (currStr.equals(endWord)) {
                    return level;
                }
                for (String neib : map.get(currStr)) {
                    if (!visited.contains(neib)) {
                        queue.add(neib);
                        visited.add(neib);
                    }
                }
            }
        }
        return 0;
    }
    
    private boolean diffBySingleLetter(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}

// Official Solution Provided by LC
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach( word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });

        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }
}


// Also could do Bidirectional Breadth First Search