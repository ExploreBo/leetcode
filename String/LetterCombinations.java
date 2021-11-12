class Solution {

    private Map<Character, List<Character>> map;
    private List<String> answer;
    char[] chars;

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.trim().equals("")) {
            return List.of();
        }

        if (map == null) {
            map = new HashMap<>();
            map.put('2', List.of('a', 'b', 'c'));
            map.put('3', List.of('d', 'e', 'f'));
            map.put('4', List.of('g', 'h', 'i'));
            map.put('5', List.of('j', 'k', 'l'));
            map.put('6', List.of('m', 'n', 'o'));
            map.put('7', List.of('p', 'q', 'r', 's'));
            map.put('8', List.of('t', 'u', 'v'));
            map.put('9', List.of('w', 'x', 'y', 'z'));
        }

        answer = new ArrayList<>();
        chars = digits.toCharArray();

        dfs(0, new StringBuilder());

        answer.sort(Comparator.naturalOrder());
        return answer;
    }

    public void dfs(int index, StringBuilder sb) {

        if (index >= chars.length) {
            answer.add(sb.toString());
            return;
        }

        List<Character> list = map.get(chars[index]);
        
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            dfs(index + 1, sb);
            sb.deleteCharAt(index);
        }
    }
}