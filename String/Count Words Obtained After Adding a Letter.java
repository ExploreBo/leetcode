class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        int count = 0;
        // process startwords
        Set<String> set = new HashSet();
        for (String statWord : startWords) {
            char[] wordArray = statWord.toCharArray();
            Arrays.sort(wordArray);
            set.add(new String(wordArray));
        }

        for (String targetWord : targetWords) {
            char[] targetWordArray = targetWord.toCharArray();
            Arrays.sort(targetWordArray);
            String candidate = new String(targetWordArray);
            for (int i = 0; i < targetWordArray.length; ++i) {
                if (set.contains(candidate.substring(0, i) + candidate.substring(i + 1))) {
                    count++;
                    break;
                }
            }

            
        }
        return count;
    }
}

// bitmask
class Solution {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> startSet = new HashSet<>();
        for(String word : startWords){
           startSet.add(toInt(word));
        }
        int ans = 0;
        for(String word : targetWords){
            int num = toInt(word);
            for(int i=0; i<26; i++){
                if((num & (1<<(i))) > 0){
                    int temp = num - (1<<(i));
                    if(startSet.contains(temp)){
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
    
    public int toInt(String s){
        int ret = 0;
        for(char c : s.toCharArray()){
            ret += (1<<(c-'a'));
        }
        return ret;
    }

}