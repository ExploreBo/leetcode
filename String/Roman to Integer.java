// brute force
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'V') {
                    result += 4;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'X') {
                    result += 9;
                    i++;  
                    continue;
                }
            }
            if (s.charAt(i) == 'X' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'L') {
                    result += 40;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'C') {
                    result += 90;
                    i++;  
                    continue;
                }
            } 
            if (s.charAt(i) == 'C' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'D') {
                    result += 400;
                    i++;
                    continue;
                }
                if (s.charAt(i + 1) == 'M') {
                    result += 900;
                    i++; 
                    continue;
                }
            }
            result += map.get(s.charAt(i));
        }
        return result;
    }
}

// left to right pass, check if the current value is larger than previous symbol's value
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i - 1 >= 0 && map.get(s.charAt(i - 1)) < map.get(s.charAt(i))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));;
            } else {
                result += map.get(s.charAt(i));    
            }
        }
        return result;
    }
}