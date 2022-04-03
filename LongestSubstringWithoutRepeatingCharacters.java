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