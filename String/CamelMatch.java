class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList();
        for (String query: queries) {
            result.add(matchHelper(query, pattern));
        }
        return result;
    }
    
    public Boolean matchHelper(String query, String pattern) {
        char[] queryArray = query.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int i = 0;
        int j = 0;
        while (i < queryArray.length && j < patternArray.length) {
            if (queryArray[i] == patternArray[j]) {
                i++;
                j++;
                continue;
            }
            if (queryArray[i] >= 'A' && queryArray[i] <= 'Z') {
                return false;
            }
            i++;
        }
        if (j < patternArray.length) {
            return false;
        }
        while (i < queryArray.length) {
            if (queryArray[i] >= 'A' && queryArray[i] <= 'Z') {
                return false;
            }
            i++;
        }
        return true;
    }
}