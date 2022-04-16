class Trie {
    TrieNode root;
    
    private class TrieNode {
        boolean isWord;
        TrieNode[] children;
        TrieNode() {
            this.isWord = false;
            this.children = new TrieNode[26];
        }
    }

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        if (word == null || word.equals("")) {
            return;
        }
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                TrieNode newNode = new TrieNode();
                curr.children[c - 'a'] = newNode;
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }
    
    public boolean search(String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.children[c - 'a'];
            if (curr == null) {
                return false;
            }
        }        
        return curr.isWord;
    }
    
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.equals("")) {
            return false;
        }
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            curr = curr.children[c - 'a'];
            if (curr == null) {
                return false;
            }
        }        
        return true;      
    }
}


/* Solution 2
Don't store isWord boolean at each node.
Instead, store the whole word at the last Node.

*/
class Trie {
    TrieNode root;
    
    private class TrieNode {
        String word;
        TrieNode[] children;
        TrieNode() {
            this.word = "";
            this.children = new TrieNode[26];
        }
    }

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        if (word == null || word.equals("")) {
            return;
        }
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                TrieNode newNode = new TrieNode();
                curr.children[c - 'a'] = newNode;
            }
            curr = curr.children[c - 'a'];
        }
        curr.word = word;
    }
    
    public boolean search(String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            curr = curr.children[c - 'a'];
            if (curr == null) {
                return false;
            }
        }        
        return curr.word != "";
    }
    
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.equals("")) {
            return false;
        }
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            curr = curr.children[c - 'a'];
            if (curr == null) {
                return false;
            }
        }        
        return true;      
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



