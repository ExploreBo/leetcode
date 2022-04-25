class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowelsSet = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));      
        
        int i = 0;
        int j = s.length() - 1;
        char[] array = s.toCharArray();
        while (i < j) {
            while (i < j && !vowelsSet.contains(array[i])) {
                i++;
            }
            while (j > i && !vowelsSet.contains(array[j])) {
                j--;
            }            
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return new String(array);
    }
}