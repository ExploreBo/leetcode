// initial version
class Solution {
    public boolean isPalindrome(String s) {
        char[] array = s.toCharArray();
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            while (start < array.length - 1 && !Character.isAlphabetic(array[start]) && !Character.isDigit(array[start])) {
                start++;
            }
            while (end >= 0 && !Character.isAlphabetic(array[end]) && !Character.isDigit(array[end])) {
                end--;
            }
            if (start < array.length - 1 && end >= 0 && Character.toLowerCase(array[start]) != (Character.toLowerCase(array[end]))) {
                return false;
            }
           start++;
           end--;
        }
        return true;
    }
}

// better version
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                if (Character.toLowerCase(s.charAt(left)) ==  Character.toLowerCase(s.charAt(right))) {
                    left++;
                    right--;
                } else {
                    return false;
                }

            }
        }
        return true;
    }
}