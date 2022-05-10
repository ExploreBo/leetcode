// recursion 
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList();
        if (digits == null || digits.length() == 0) return result;
        
        Map<Character, List<String>> map = new HashMap();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        

        StringBuilder sb = new StringBuilder();
        dfs(digits, sb, 0, result, map);
        return result;
    }
    
    private void dfs (String digits, StringBuilder sb, int index, List<String> result, Map<Character, List<String>> map) {
        if (index == digits.length()) {
            result.add(sb.toString());
        } else {
            List<String> candidates = map.get(digits.charAt(index));
            for (String candidate : candidates) {
                sb.append(candidate);
                dfs(digits, sb, index + 1, result, map);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

// iteration with queue. BFS
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }