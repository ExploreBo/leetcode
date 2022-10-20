/*

The BFS runs at O(target * log(target)) in the worst case, with O(target * log(target)) space. 
The reasoning is as follows: in the worst case, 
all positions in the range [-target, target] will be visited 
and for each position there can be as many as 2 * log(target) different speeds.
*/
class Solution {    
    public int racecar(int target) {
        Set<String> visited = new HashSet<>(); 
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // speed is key , pos is value
        queue.add(new Pair(1, 0));
        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Pair<Integer, Integer> cur = queue.poll();
                if (cur.getValue() == target) {
                    return distance;
                }
                // if A
                int nextPosition = cur.getValue() + cur.getKey();
                int nextSpeed = cur.getKey() * 2;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    queue.offer(new Pair(nextSpeed, nextPosition));
                }             
                // if R
                nextPosition = cur.getValue();
                nextSpeed = cur.getKey() > 0 ? -1 : 1;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    queue.offer(new Pair(nextSpeed, nextPosition));
                }
            }
            distance++;
        }
        return -1;
    }
}
