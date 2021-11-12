class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String minusOne = countAndSay(n - 1);
        char[] minusOneArray = minusOne.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minusOneArray.length; i++) {
            int count = 1;
            char curr = minusOneArray[i];
            while (i + 1 < minusOneArray.length && minusOneArray[i + 1] == curr) {
                count++;
                i++;
            }
            sb.append(String.valueOf(count));
            sb.append(curr);
        }
        return sb.toString();
        
    }
}