// brute force
class Solution {
    private Set<String> result;
    public List<String> wordBreak(String s, List<String> wordDict) {
        result = new HashSet();
        wordBreak(s, new HashSet<>(wordDict), 0, new StringBuilder());
        return new ArrayList(result);
    }

    private void wordBreak(String s, Set<String> wordDict, int start, StringBuilder sb) {
        if (start == s.length()) {
            sb.setLength(sb.length() - 1);
            result.add(sb.toString());
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String candidate = s.substring(start, end);
            if (wordDict.contains(candidate)) {
                int len = sb.length();
                sb.append(candidate);
                sb.append(" ");
                wordBreak(s, wordDict, end, sb);
                sb.setLength(len);
            }
        }
    }
}

// brute force with memorization
class Solution {
    private List<String>[] memo;
    public List<String> wordBreak(String s, List<String> wordDict) {
        memo = new List[s.length() + 1];
        wordBreak(s, new HashSet<>(wordDict), 0);
        return memo[0];
    }

    private void wordBreak(String s, Set<String> wordDict, int start) {
        if (memo[start] != null) {
            return;
        }
        memo[start] = new ArrayList();
        for (int end = start + 1; end <= s.length(); end++) {
            String candidate = s.substring(start, end);
            if (wordDict.contains(candidate)) {
                wordBreak(s, wordDict, end);
                if (end == s.length()) {
                    memo[start].add(candidate);
                }
                for (String suffix : memo[end]) {
                    memo[start].add(candidate + " " + suffix);
                }
            }
        }
    }
}