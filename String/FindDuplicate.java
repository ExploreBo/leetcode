class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap();
        for (String path : paths) {
            String[] files = path.split(" ");
            for (int i = 1; i < files.length; i++) {
                int contentStart = files[i].indexOf("(") ;
                int contentEnd = files[i].indexOf(")");
                String file = files[i].substring(0, contentStart);
                String content = files[i].substring(contentStart + 1, contentEnd);
                List<String> list = new ArrayList();
                list = map.get(content);
                if (list == null) {
                    map.put(content, new ArrayList(Arrays.asList(files[0] + "/" + file)));
                } else {
                    list.add(files[0] + "/" + file);
                    map.put(content, list);
                }
            }
        }
        List<List<String>> result = new ArrayList();
        for (List list : map.values()) {
            if (list.size() > 1) {
                result.add(list);   
            }
        }
        return result;
    }
}