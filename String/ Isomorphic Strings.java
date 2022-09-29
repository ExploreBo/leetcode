// First occurence transformation
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] firstIndexS = new int[256];
        int[] firstIndexT = new int[256];
        int index = 0;
        while (index < s.length()) {
            if (firstIndexT[t.charAt(index)] != firstIndexS[s.charAt(index)]) {
                return false;
            }
            firstIndexS[s.charAt(index)] = index + 1;
            firstIndexT[t.charAt(index)] = index + 1;            
            index++;
        }
        return true;
    }
}
