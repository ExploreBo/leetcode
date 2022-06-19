class Solution {
    public String reorganizeString(String s) {
        char[] charCount = new char[26];
        int max = 0;
        int sum = 0;
        int maxIndex = 0;
        for (char c : s.toCharArray()) {
            sum++;
            int index = c - 'a';
            charCount[index]++;
            if (charCount[index] > max) {
                max = charCount[index];
                maxIndex = index;
            }
        }
        if (sum - max < max - 1) return "";
        
        char[] res = new char[s.length()];
        int idx = 0;
        while (charCount[maxIndex] > 0) {
            res[idx] = (char) (maxIndex + 'a');
            idx += 2;
            charCount[maxIndex]--;
        }        
        for (int i = 0; i < charCount.length; i++) {
            while (charCount[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                charCount[i]--;
            }
        }
        return String.valueOf(res);
    }
}

// Priority queue
class Solution {
    public String reorganizeString(String s) {
        // Create map of each char to its count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (s.length() + 1) / 2) return "";
            map.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : map.keySet()) {
            pq.add(new int[] {c, map.get(c)});
        }
        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }
}