// brute force 
class Solution {
    public String longestWord(String[] words) {
        String longestWord = "";
        // edge cases
        if (words == null || words.length == 0) return longestWord;
        
        Set<String> wordsSet = new HashSet();
        for (String word : words) {
            wordsSet.add(word);
        }
        
        for (String word : words) {
            boolean validString = true;
            for (int k = 1; k < word.length(); k++) {
                if (!wordsSet.contains(word.substring(0, k))) {
                    validString = false;
                    break;
                }
            }
            if (validString) {
                if (word.length() > longestWord.length() || 
                   word.length() == longestWord.length() && word.compareTo(longestWord) < 0) {
                    longestWord = word;
                }
            }
        }
        return longestWord;
    }
}

// Trie
class Trie {
    Trie[] children;
    boolean isWord;
    
    public Trie() {
        this.children = new Trie[26];
        this.isWord = false;
    }
    
} 
class Solution {
    public String longestWord(String[] words) {
        String result = "";
        // construct Trie Tree
        Trie root = new Trie();
        for (String word : words) {
            Trie currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (currentNode.children[word.charAt(i) - 'a'] == null) {
                    currentNode.children[word.charAt(i) - 'a'] = new Trie();
                }
                currentNode = currentNode.children[word.charAt(i) - 'a'];
                if (i == word.length() - 1) {
                    currentNode.isWord = true;
                }
            }
        }
        
        for (String word : words) {
            Trie currentNode = root;
            boolean isValidString = true;
            for (int i = 0; i < word.length(); i++) {
                if (currentNode.children[word.charAt(i) - 'a'] == null) {
                    isValidString = false;
                    break;
                } else {
                    currentNode = currentNode.children[word.charAt(i) - 'a'];
                    if (currentNode.isWord == false) {
                        isValidString = false;
                        break;
                    }                    
                }
            }
            if (isValidString) {
                if (word.length() > result.length() || 
                   word.length() == result.length() && word.compareTo(result) < 0) {
                    result = word;
                }
            }
        }
        return result;
    }
}

// Trie + BFS
class Trie {
    Trie[] children;
    boolean isWord;
    String word;
    
    public Trie() {
        this.children = new Trie[26];
        this.isWord = false;
    }
    
} 
class Solution {
    public String longestWord(String[] words) {
        String result = "";
        // construct Trie Tree
        Trie root = new Trie();
        for (String word : words) {
            Trie currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (currentNode.children[word.charAt(i) - 'a'] == null) {
                    currentNode.children[word.charAt(i) - 'a'] = new Trie();
                }
                currentNode = currentNode.children[word.charAt(i) - 'a'];
                if (i == word.length() - 1) {
                    currentNode.isWord = true;
                    currentNode.word = word;
                }
            }
        }
        
        Queue<Trie> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Trie node = queue.poll();
                for (int j = 25; j >= 0; j--) {
                    if (node.children[j] != null && node.children[j].isWord) {
                        result = node.children[j].word;
                        queue.offer(node.children[j]);
                    }
                }
            }
        }
        return result;
    }
}