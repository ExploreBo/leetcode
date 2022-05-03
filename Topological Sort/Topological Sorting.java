// DFS + stack
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList();
        Stack<DirectedGraphNode> stack = new Stack();
        Set<DirectedGraphNode> seen = new HashSet<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            dfs(node, stack, seen);
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void dfs(DirectedGraphNode currentNode, Stack<DirectedGraphNode> stack, Set<DirectedGraphNode> seen) {
        if (seen.contains(currentNode)) {
            return;
        } else {
            for (DirectedGraphNode nextNode : currentNode.neighbors) {
                dfs(nextNode, stack, seen);
            }
            seen.add(currentNode);
            stack.add(currentNode);            
        }
    }
}

// BFS: 1. get the 0 inDegree nodes via Map. 2. Use queue, iterate starting from 0 indegree nodes.
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        if (graph == null || graph.size() < 1) {
            return new ArrayList<>();
        }
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neigh : node.neighbors) {
                inMap.putIfAbsent(node, 0);
                inMap.put(neigh, inMap.getOrDefault(neigh, 0) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode key : inMap.keySet()) {
            if (inMap.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            res.add(node);
            for (DirectedGraphNode neigh : node.neighbors) {
                inMap.put(neigh, inMap.get(neigh) - 1);
                // no other nodes should come before the current node
                if (inMap.get(neigh) == 0) {
                    queue.offer(neigh);
                }
            }
        }

        return res;

    }
}




/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     List<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<DirectedGraphNode>();
 *     }
 * }
 */