// TLE
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int row = 0;
        int col = cols;
        int index = 0;
        int count = 0;
        while (row < rows) {
            int wordLength = sentence[index].length();
            if (wordLength > cols) {
                return 0;
            }            
            if (wordLength == col) {
                ++row;
                col = cols;
            } else if (wordLength > col) {
                ++row;
                if (row >= rows) {
                    break;
                }
                col = cols;
                col -= wordLength + 1;
            } else {
                col -= wordLength + 1;
            }
            ++index;
            if (index == sentence.length) {
                ++count;
                index = 0;
            }
        }
        return count;
    }
}


public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }
}