// recursion
class Solution {
    private List<String> result;
    private int n;
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        result = new ArrayList();
        this.n = n;
        dfs(0, 0, new StringBuilder());
        return result;
    }
    
    private void dfs(int startCount, int endCount, StringBuilder sb) {  
        if (startCount == endCount && endCount == n) {
            result.add(sb.toString());
        } else if (startCount == endCount) {
            dfs(startCount + 1, endCount, sb.append("("));
            sb.deleteCharAt(sb.length() - 1);
        } else {
            if (startCount + 1 <= n) {
                dfs(startCount + 1, endCount, sb.append("(")); 
                sb.deleteCharAt(sb.length() - 1);
            }
            dfs(startCount, endCount + 1, sb.append(")")); 
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

// another version of recursion
class Solution {
    private List<String> result;
    private int n;
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        result = new ArrayList();
        this.n = n;
        dfs(0, 0, new StringBuilder());
        return result;
    }
    
    private void dfs(int startCount, int endCount, StringBuilder sb) {  
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        } 
        if (startCount < n) {
            dfs(startCount + 1, endCount, sb.append("(")); 
            sb.deleteCharAt(sb.length() - 1);            
        }
        if (endCount < startCount) {
            dfs(startCount, endCount + 1, sb.append(")")); 
            sb.deleteCharAt(sb.length() - 1);            
        }
    }
}

// closure number
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}