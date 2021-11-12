class Solution {
    public String toLowerCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] <= 'Z' && charArray[i] >= 'A') {
                charArray[i] += 32;
            }
        }        
        return new String(charArray);
    }
}
