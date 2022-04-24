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
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }

      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
        return false;
    }

    return true;
  }
}