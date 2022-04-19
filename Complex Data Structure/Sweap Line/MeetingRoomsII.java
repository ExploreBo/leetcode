// sweap line, the same as number of airplanes in the sky
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int now = 0;
        int max = 0;
        int i = 0; int j = 0;
        while (i < intervals.length) {
            if (starts[i] < ends[j]) {
                now++;
                i++;
                max = Math.max(now, max);
            } else {
                now--;
                j++;
            }
        }
        return max;        
    }
}