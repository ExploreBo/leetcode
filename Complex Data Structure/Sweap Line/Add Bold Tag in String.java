// my solution
class Solution {
    public String addBoldTag(String s, String[] words) {
        PriorityQueue<Pair<Integer, Integer>> intervals = new PriorityQueue<Pair<Integer, Integer>>((a, b) -> a.getKey() - b.getKey());
        for (String word : words) {
            int index = 0;
            int start = s.indexOf(word, index);
            while (start != -1) {
                index++;
                intervals.add(new Pair(start, start + word.length()));
                start = s.indexOf(word, index);
            }
        }
        
        List<Pair<Integer, Integer>> mergedIntervals = new ArrayList();
        while (!intervals.isEmpty()) {
            Pair<Integer, Integer> curInterval = intervals.poll();
            if (mergedIntervals.isEmpty()) {
                mergedIntervals.add(curInterval);
            } else {
                Pair<Integer, Integer> preInterval = mergedIntervals.get(mergedIntervals.size() - 1);
                if (curInterval.getKey() >preInterval.getValue()) {
                    mergedIntervals.add(curInterval);
                } else {
                    mergedIntervals.remove(mergedIntervals.size() - 1);
                    mergedIntervals.add(new Pair(preInterval.getKey(), Math.max(preInterval.getValue(), curInterval.getValue())));

                }
            }
        }
        StringBuilder sb = new StringBuilder(s);
        int openOffSet = 0;
        int closeOffSet = 0;
        for (Pair<Integer, Integer> interval : mergedIntervals) {
            sb.insert(interval.getKey() + openOffSet++ * 3 + closeOffSet * 4, "<b>");
            sb.insert(interval.getValue() + openOffSet * 3 + closeOffSet++ * 4, "</b>");
        }
        return sb.toString();

        
    }
}

// leetcode solution
public class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }
        
        return result.toString();
    }
}