/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>(); // Key: time point, value: score.
        for (List<Interval> list : schedule) {
            for (Interval interval : list) {
                map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
                map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
            }
        }

        int start = -1, score = 0;
        for (int point : map.keySet()) {
            score += map.get(point);
            if (score == 0 && start == -1) {
                start = point;
            } else if (start != -1 && score != 0) {
                result.add(new Interval(start, point));
                start = -1;
            }
        }

        return result;        
    }
}

// priority queue
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);
        for (int i = 0; i < schedule.size(); i++) {
            pq.add(new int[] {i, 0});
        }
        List<Interval> res = new ArrayList<>();
        int prev = schedule.get(pq.peek()[0]).get(pq.peek()[1]).end;
        while (!pq.isEmpty()) {
            int[] index = pq.poll();
            Interval interval = schedule.get(index[0]).get(index[1]);
            if (interval.start > prev) {
                res.add(new Interval(prev, interval.start));
            }
            prev = Math.max(prev, interval.end);
            if (schedule.get(index[0]).size() > index[1] + 1) {
                pq.add(new int[] {index[0], index[1] + 1});
            }
        }
        return res;
    }
}