/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) return result;
        
        Queue<Node> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                if (currentNode != null) {
                    level.add(currentNode.val);
                    for (Node child: currentNode.children) {
                        queue.add(child);
                    }
                }
            }
            if (!level.isEmpty()) {
                result.add(level);
            }
        }
        return result;
    }
}