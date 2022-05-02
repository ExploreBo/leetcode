// each step of iterating the queue, the size of queue should be 1
// otherwise there would be multiple ways to iterate so it won't be the only supersequence and should return false
// Also need to check whether it is the shortest (org.length == indegree.size()) and a super sequence (cnt == indegree.size())
public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, Integer> indegree = new HashMap<>();
    for (List<Integer> sequence: sequences) {
        for (int i : sequence) {
            map.putIfAbsent(i, new HashSet<>());
            indegree.putIfAbsent(i, 0);
        }
    }
		
    for (List<Integer> sequence: sequences) {
        // no info is revealed in a single digit
        if (seq.length == 1) continue;
        for (int i = 1; i < seq.length; i++)
            if (map.get(seq[i - 1]).add(seq[i]))
                indegree.put(seq[i], indegree.get(seq[i]) + 1);
    }
		
    // make sure it is the shortest
    if (nums.length != indegree.size()) return false;
		
    Queue<Integer> q = new ArrayDeque<>();
    for (int key : indegree.keySet()) 
        if (indegree.get(key) == 0) q.add(key);
		
    int cnt = 0;
    while (q.size() == 1) {
        for (int next : map.get(q.poll())) {
            indegree.put(next, indegree.get(next) - 1);
            if (indegree.get(next) == 0) q.add(next);
        }
        cnt++;
    }
		
    // it could finish the topological sorting
    return cnt == indegree.size();
}