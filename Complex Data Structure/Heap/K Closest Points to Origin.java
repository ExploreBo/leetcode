/*
Solution I: Sort with Custom Comparator
Time complexity: O(N⋅logN), N refers to the length of the given array points.
*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Sort the array with a custom lambda comparator function
        Arrays.sort(points, (a, b) -> squaredDistance(a) - squaredDistance(b));
        
        // Return the first k elements of the sorted array
        return Arrays.copyOf(points, k);
    }
    
    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
};

/*
Solution II: Max Heap (Priority Queue)
Time complexity: O(N⋅logk)  
*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Use a lambda comparator to sort the PQ by farthest distance
        Queue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < points.length; i++) {
            int[] entry = {squaredDistance(points[i]), i};
            if (maxPQ.size() < k) {
                // Fill the max PQ up to k points
                maxPQ.add(entry);
            } else if (entry[0] < maxPQ.peek()[0]) {
                // If the max PQ is full and a closer point is found,
                // discard the farthest point and add this one
                maxPQ.poll();
                maxPQ.add(entry);
            }
        }
        
        // Return all points stored in the max PQ
        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            int entryIndex = maxPQ.poll()[1];
            answer[i] = points[entryIndex];
        }
        return answer;
    }
    
    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
};

/*
Solution III: Modified Binary Search
Time complexity: O(N), N refers to the length of the given array points.
*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Precompute the Euclidean distance for each point,
        // define the initial binary search range,
        // and create a reference list of point indices
        double[] distances = new double[points.length];
        double low = 0, high = 0;
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            distances[i] = euclideanDistance(points[i]);
            high = Math.max(high, distances[i]);
            remaining.add(i);
        }

        // Perform a binary search of the distances
        // to find the k closest points
        List<Integer> closest = new ArrayList<>();
        while (k > 0) {
            double mid = low + (high - low) / 2;
            List<List<Integer>> result = splitDistances(remaining, distances, mid);
            List<Integer> closer = result.get(0), farther = result.get(1);
            if (closer.size() > k) {
                // If more than k points are in the closer distances
                // then discard the farther points and continue
                remaining = closer;
                high = mid;
            } else {
                // Add the closer points to the answer array and keep
                // searching the farther distances for the remaining points
                k -= closer.size();
                closest.addAll(closer);
                remaining = farther;
                low = mid;
            }
        }
        
        // Return the k closest points using the reference indices
        k = closest.size();
        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            answer[i] = points[closest.get(i)];
        }
        return answer;
    };
    
    private List<List<Integer>> splitDistances(List<Integer> remaining, double[] distances,
                                               double mid) {
        // Split the distances around the midpoint
        // and return them in separate lists
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> closer = new ArrayList<>();
        List<Integer> farther = new ArrayList<>();
        result.add(closer);
        result.add(farther);
        for (int point : remaining) {
            if (distances[point] <= mid) {
                closer.add(point);
            } else {
                farther.add(point);
            }
        }
        return result;
    }
    
    private double euclideanDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
}

/*
Solution IV: Quick Select
Time complexity: O(N), N refers to the length of the given array points.
*/
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        return quickSelect(points, k);
    }
    
    private int[][] quickSelect(int[][] points, int k) {
        int left = 0, right = points.length - 1;
        int pivotIndex = points.length;
        while (pivotIndex != k) {
            // Repeatedly partition the array
            // while narrowing in on the kth element
            pivotIndex = partition(points, left, right);
            if (pivotIndex < k) {
                left = pivotIndex;
            } else {
                right = pivotIndex - 1;
            }
        }
        
        // Return the first k elements of the partially sorted array
        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int left, int right) {
        int[] pivot = choosePivot(points, left, right);
        int pivotDist = squaredDistance(pivot);
        while (left < right) {
            // Iterate through the range and swap elements to make sure
            // that all points closer than the pivot are to the left
            if (squaredDistance(points[left]) >= pivotDist) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp; 
                right--;
            } else {
                left++;
            }
        }
        
        // Ensure the left pointer is just past the end of
        // the left range then return it as the new pivotIndex
        if (squaredDistance(points[left]) < pivotDist)
            left++;
        return left;
    }

    private int[] choosePivot(int[][] points, int left, int right) {
        // Choose a pivot element of the array
        return points[left + (right - left) / 2];
    }
    
    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
}