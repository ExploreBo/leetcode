class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] sArray = s.toCharArray();
        if (sArray.length == 0) {return 0;}
        int sum = 1;
        int max = 1;
        int start = 0;
        int index = 1;
        Set subString = new HashSet<>();
        subString.add(sArray[0]);
        while (start + index < sArray.length) {
            if (!subString.contains(sArray[start + index])) {
                subString.add(sArray[start + index]);
                index++;
                sum++;
                max = Math.max(max, sum);
            } else {
                char target = sArray[start + index];
                while (start < sArray.length && sArray[start] != target) {
                    subString.remove(sArray[start]);
                    sum--;
                    start++;
                    index--;
                }
                start++;
            }
        }
        return max;
    }    
}

// Solution 2: also using HashSet but more clear
class Solution {
    public int lengthOfLongestSubstring(String s) {        
        int maxLen = 0;
        Set<Character> window = new HashSet<>(); 
        
        int left = 0, right = 0;
        while(right < s.length()) { 
            while(window.contains(s.charAt(right)))
                window.remove(s.charAt(left++));  
            window.add(s.charAt(right++)); 
            maxLen = Math.max(maxLen, right - left);
        }
         
        return maxLen;
    }
}

// Solution 3
class Solution {
   public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }
}
