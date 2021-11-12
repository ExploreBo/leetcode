class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet();
        for (String email : emails) {
            String convertedEmail = convertEmail(email);
            if (!set.contains(convertedEmail)) {
                set.add(convertedEmail);
            }
        }
        return set.size();
    }
    
    
    public String convertEmail(String email) {
        char[] array = email.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean beforeAt = true;
        boolean afterPlus = false;
        for (int i =0; i < array.length; i++) {
            if (array[i] == '@') {
               beforeAt = false;
                sb.append(array[i]);
            }
            if (array[i] == '+') {
                afterPlus = true;
                if (beforeAt) {
                    continue;
                }
            }
            if (array[i] == '.') {
                if (beforeAt) {
                    continue;
                } else {
                    sb.append(array[i]);
                }
            }
            if (beforeAt && afterPlus) {
                continue;
            } else {
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }
}