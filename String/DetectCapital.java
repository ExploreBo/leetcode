class Solution {
    public boolean detectCapitalUse(String word) {
        char[] c = word.toCharArray();
        if (c[0] <= 'Z' && c[0] >= 'A') {
            return detectRest(c, 'A') || detectRest(c, 'a');
        } else {
            return detectRest(c, 'a');
        }
    }
    
    public boolean detectRest(char[] c, char min) {
        for (int i = 1; i < c.length; i++) {
            if (c[i] - min < 32 && c[i] - min >= 0) {
                continue;  
            } else {
                return false;
            }
        }
        return true;
    }

    // solution 2
    public boolean detectCapitalUse(String word) {
        boolean case1 = false; //USA
        boolean case2 = false;//leetcode
        boolean case3 = false;//Google
        if (word.charAt(0)>='A'&&word.charAt(0)<='Z') { //if the first one is captial letter
            case1 = true; 
            case3 = true;
        }
        else case2 = true;
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c>='A' && c<='Z') {//except for the first letter,as soon as capital letters appear, the case2 and case3 are no longer satisfied      
                case2 = false;
                case3 = false;
            }
            else 
            {
                case1 = false;     
            }        
        }
        return case1 || case2 || case3; //if one of them meet the requirement, we can return true
    }
}