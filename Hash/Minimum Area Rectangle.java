class Solution {
    public int minAreaRect(int[][] points) {
        int minArea = Integer.MAX_VALUE;
        if (points.length < 3) {
            return minArea;
        }
        Set<Pair<Integer, Integer>> pointsSet = new HashSet();
        for (int i = 0; i < points.length; ++i) {
            pointsSet.add(new Pair(points[i][0], points[i][1]));
        }
        
        for (int i = 1; i < points.length; ++i) {
            int[] p1 = points[i];
            for (int j = i - 1; j >= 0; j--) {
                int[] p3 = points[j];
                if (p1[0] == p3[0] || p1[1] == p3[1]) {
                    continue;
                }
                if (pointsSet.contains(new Pair(p1[0], p3[1])) && pointsSet.contains(new Pair(p3[0], p1[1]))) {
                    minArea = Math.min(minArea, Math.abs(p1[0] - p3[0]) * Math.abs(p1[1] - p3[1]));
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea ;
    }
}