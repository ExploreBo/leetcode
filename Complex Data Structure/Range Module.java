// tree map
class RangeModule {
    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public void addRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        if (start == null) start = map.ceilingKey(left);
        while (start != null && start <= right) {
            int end = map.get(start);
            if (end >= left) {
                map.remove(start);
                if (start < left) left = start;
                if (end > right) right = end;
            }
            start = map.ceilingKey(end);
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer floor = map.floorKey(left);
        return floor != null && map.get(floor) >= right;
    }

    public void removeRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        if (start == null) start = map.ceilingKey(left);
        while (start != null && start < right) {
            int end = map.get(start);
            if (end >= left) {
                map.remove(start);
                if (start < left) map.put(start, left);
                if (end > right) map.put(right, end);
            }
            start = map.ceilingKey(end);
        }
    }
}


// array
class RangeModule {
    List<int[]> ranges;

    public RangeModule() {
        ranges = new ArrayList();
    }
    
    public void addRange(int left, int right) {
        if (right <= left) return;
        if (ranges.size() == 0) {
            ranges.add(new int[]{left, right});
        } else {
            List<int[]> newRanges = new ArrayList();
            int index = 0;
            while (index < ranges.size()) {
                int[] range = ranges.get(index);
                if (left > range[1]) {
                    newRanges.add(range);
                    index++;
                } else {
                    newRanges.add(new int[]{left, right});
                    break;
                }
            }
            while (index < ranges.size()) {
                int[] pre = newRanges.get(newRanges.size() - 1);
                int[] cur = ranges.get(index);
                if (cur[0] > pre[1]) {
                    newRanges.add(cur);
                } else {
                    pre[0] = Math.min(pre[0], cur[0]);
                    pre[1] = Math.max(pre[1], cur[1]);
                }
                index++;
            }
            if (left > newRanges.get(newRanges.size() - 1)[1]) {
                newRanges.add(new int[]{left, right});
            }
            ranges = newRanges;
        }

        
    }
    
    public boolean queryRange(int left, int right) {
        if (left >= right) return false;
        for (int[] range : ranges) {
            if (left >= range[0] && right <= range[1]) {
                return true;
            }
            if (right <= range[0]) {
                break;
            }
        }
        return false;
    }
    
    public void removeRange(int left, int right) {
        if (right <= left) return;
        List<int[]> newRanges = new ArrayList();
        int index = 0;
        while (index < ranges.size()) {
            int[] range = ranges.get(index);
            if (left >= range[1] || right <= range[0]) {
                newRanges.add(range);
            } else {
                if (range[0] < left) {
                    add(newRanges, range[0], left);
                }
                if (range[1] > right) {
                    add(newRanges, right, range[1]);
                }
            }
            index++;
        }
        ranges = newRanges;
    }

    private void add(List<int[]> ranges, int start, int end) {
        if (start == end) {
            ranges.add(new int[]{start, start + 1});
        } else if (start < end) {
            ranges.add(new int[]{start, end});
        }
    }
}