// interactive DFS
public boolean validTree(int n, int[][] edges) {
            
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    
    Map<Integer, Integer> parent = new HashMap<>();
    parent.put(0, -1);
    Stack<Integer> stack = new Stack<>();
    stack.push(0);

    while (!stack.isEmpty()) {
        int node = stack.pop();
        for (int neighbour : adjacencyList.get(node)) {
            // avoid going back to the node where it comes from, it will cause trivial cycle, a -> b -> a ...
            if (parent.get(node) == neighbour) {
                continue;
            }
            // a cycle is detected
            if (parent.containsKey(neighbour)) {
                return false;
            }
            stack.push(neighbour);
            parent.put(neighbour, node);
        }
    }
    // check if every node is connected
    return parent.size() == n;   
}


/* interactive DFS based on the fact of advanced graph theory:
For the graph to be a valid tree, it must have exactly n - 1 edges. 
Any less, and it can't possibly be fully connected. 
Any more, and it has to contain cycles. 
Additionally, if the graph is fully connected and contains exactly n - 1 edges, 
it can't possibly contain a cycle, and therefore must be a tree!
*/
public boolean validTree(int n, int[][] edges) {
        
    if (edges.length != n - 1) return false;
    
    // Make the adjacency list.
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    
    Stack<Integer> stack = new Stack<>();
    Set<Integer> seen = new HashSet<>();
    stack.push(0);
    seen.add(0);
    
    while (!stack.isEmpty()) {
        int node = stack.pop();
        for (int neighbour : adjacencyList.get(node)) {
            if (seen.contains(neighbour)) continue;
            seen.add(neighbour);
            stack.push(neighbour);
        }
    }
    
    return seen.size() == n;   
}


// This could be also resolved by Union Find given the advanced graph theory.

