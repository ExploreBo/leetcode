class Solution {
    public String entityParser(String text) {
        Map<String, Character> map = new HashMap();
        map.put("&quot;", '"');
        map.put("&apos;", '\'');
        map.put("&amp;", '&');
        map.put("&gt;", '>');
        map.put("&lt;", '<');
        map.put("&frasl;", '/');
                
        char[] array = text.toCharArray();
        StringBuilder ret = new StringBuilder();
        for (int i =0; i < array.length; i++) {
            if (array[i] != '&') {
                ret.append(array[i]);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append('&');
                while (i + 1 < array.length) {
                    if (array[i + 1] == '&') {
                        ret.append(sb.toString());
                        sb = new StringBuilder(sb.substring(0, 0));
                        
                    }
                    sb.append(array[i + 1]);
                    i++;
                    if (map.containsKey(sb.toString())) {
                        ret.append(map.get(sb.toString()));
                        break;
                    }
                    if (sb.length() == 7) {
                        ret.append(sb.toString());
                        break;
                    }
                }
            }
        }
        return ret.toString();
    }
}