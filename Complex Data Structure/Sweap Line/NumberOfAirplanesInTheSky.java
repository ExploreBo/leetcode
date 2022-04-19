// LintCode: https://www.lintcode.com/problem/391
/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

// brute force: n^2
public class Solution {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        int result = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap();
        for (Interval airplane : airplanes) {
            for (int i = airplane.start; i < airplane.end; i++) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                max = Math.max(max, map.get(i));
            }
        }
        return max;
    }
}


/*
Sweap line: use TreeMap nlogn
*/
public class Solution {

    public int countOfAirplanes(List<Interval> airplanes) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Interval interval : airplanes) {
            map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
            map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
        }
        int count = 0, now = 0;
        for (Integer key : map.keySet()) {
            now += map.get(key);
            count = Math.max(count, now);
        }
        return count;
    }
}


/*
Sweap line: use two sorted Array
*/
public class Solution {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        int[] takeOff = new int[airplanes.size()];
        int[] land = new int[airplanes.size()];
        for (int i = 0; i < airplanes.size(); i++) {
            takeOff[i] = airplanes.get(i).start;
            land[i] = airplanes.get(i).end;
        }
        Arrays.sort(takeOff);
        Arrays.sort(land);
        int i = 0; int j = 0;
        int now = 0;
        int max = 0;
        while (i < airplanes.size()) {
            if (takeOff[i] < land[j]) {
                now++;
                max = Math.max(max, now);
                i++;
            } else if (takeOff[i] > land[j]) {
                now--;
                j++;
            } else {
                i++;
                j++;
            }
        }
        return max;
    }
}
