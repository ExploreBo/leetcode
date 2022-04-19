class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 1; i < intervals.length; i++) {
            // the next meeting should always start after the last meeting ends. otherwise, return false
            if (starts[i] < ends[i - 1]) {
                return false;
            }
        }
        return true;
    }
}