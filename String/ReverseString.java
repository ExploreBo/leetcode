class Solution {
    public void reverseString1(char[] s) {
        char temp;
        for (int i = 0; i < s.length / 2; i++) {
            temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    // solution 2
    public void reverseString2(char[] s) {
	    int i = 0, j = s.length - 1;
	    while (i < j){
	        char temp = s[i];
	        s[i++] = s[j];
	        s[j--] = temp;
	    }
    } 
}