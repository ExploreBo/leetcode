// TreeMap
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> startPoints = new TreeMap<>();
        for(int i=0; i<intervals.length; i++){
            startPoints.put(intervals[i][0], i);
        }
        
        int[] ans = new int[intervals.length];
        for(int i=0; i<intervals.length; i++){
            Integer nextClosestStartPoint = startPoints.ceilingKey(intervals[i][1]);
            ans[i]= nextClosestStartPoint==null ? -1 : startPoints.get(nextClosestStartPoint);
        }
        return ans;
    }
}

// Two heaps
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        PriorityQueue<int[]> minheapStart = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> minheapEnd = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Build the 2 minHeaps
        for(int i=0; i < intervals.length; i++){
            minheapStart.add(new int[]{intervals[i][0], i});
            minheapEnd.add(new int[]{intervals[i][1], i});
        }
        
        // Define the final result[]
        int[] result = new int[intervals.length];
        
        // Initialize the result[] with -1
        for(int i=0; i < intervals.length; i++)
            result[i] = -1;
        
        while(!minheapEnd.isEmpty()){
            // Fetch the smallest end index
            int[] currEnd = minheapEnd.poll();
            int currEndVal = currEnd[0];
            int currEndIdx = currEnd[1];
            
            // Find the first start idx which is greater than or equal to the current end
            while(!minheapStart.isEmpty() && currEndVal > minheapStart.peek()[0])
                minheapStart.poll();
            
            // Since there are no more elements left in minheapStart, that means rest all indices in result[] will be 
            // mapped to -1
            if(minheapStart.isEmpty())
                return result;
            
            // When minheapStart is not empty, then the top most element of minheapStart must be >= currEndVal
            // So we place the corresponding index of the top most element of minheapStart in the corresponding 
            // currEndIdx of the result[]
            result[currEndIdx] = minheapStart.peek()[1];
        }
        return result;
    }
}

// sweep line
 int[] res = new int[intervals.length];
 Arrays.fill(res, -1);
 
 List<Point> points = new ArrayList<>();
 for (int i = 0; i < intervals.length; i++) {
     points.add(new Point(intervals[i].start, 1, i));
     points.add(new Point(intervals[i].end, 0, i));
 }
 
 Collections.sort(points);
 
 List<Integer> prevIdxs = new ArrayList<>();
 
 for (Point point: points) {
     if (point.flag == 1) {
             for (Integer prevIdx: prevIdxs) {
                res[prevIdx] = point.index; 
             }
             prevIdxs = new ArrayList<>();
     } else {
         prevIdxs.add(point.index);
     }
 }
 
 return res;