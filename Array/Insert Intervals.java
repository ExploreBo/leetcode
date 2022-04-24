class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        int i = 0;
        boolean isUsed = false;        
        while (i < intervals.length && intervals[i][0] < newInterval[0]) {
            merged.add(intervals[i++]);
        }
        if (i > 0) {
            intervals[--i] = newInterval;
        } else {
            merged.add(newInterval);
        }

        while (i < intervals.length) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.getLast()[1] < intervals[i][0]) {
                merged.add(intervals[i++]);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], intervals[i++][1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}    
