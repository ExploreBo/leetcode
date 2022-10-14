// Brute Force
class Solution {
    char[] ca, cb;
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ca = S.toCharArray();
        for (String word: words)
            if (subseq(word)) ans++;
        return ans;
    }

    public boolean subseq(String word) {
        int i = 0;
        cb = word.toCharArray();
        for (char c: ca) {
            if (i < cb.length && c == cb[i]) i++;
        }
        return (i == cb.length);
    }
}

// Next Letter Pointers
// the idea is to iterate the string S only once
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            heads[i] = new ArrayList<Node>();

        for (String word: words)
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));

        for (char c: S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node: old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

}

class Node {
    String word;
    int index;
    public Node(String w, int i) {
        word = w;
        index = i;
    }
}