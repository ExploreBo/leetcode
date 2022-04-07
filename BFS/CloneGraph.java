/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// BFS
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new Node(node.val);
        }
        HashMap<Integer, Node> map = new HashMap();
        map.put(node.val, new Node(node.val));
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() != 0) {
            Node oldNode = queue.poll();
            Node target = map.get(oldNode.val);
            for (Node neighbor : oldNode.neighbors) {            
                if (!map.containsKey(neighbor.val)) {
                    map.put(neighbor.val, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                target.neighbors.add(map.get(neighbor.val));
            }
            
        }
        
        return map.get(node.val);
    }
}