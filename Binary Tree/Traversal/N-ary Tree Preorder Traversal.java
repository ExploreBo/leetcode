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
    public List<Integer> preorder(Node root) {
        List<Integer> preOrderList = new ArrayList();
        preOrderTraversalHelper(root, preOrderList);
        return preOrderList;        
    }
    private void preOrderTraversalHelper(Node root, List<Integer> preOrderList) {
        if (root != null) {
            preOrderList.add(root.val);
            for (Node child : root.children) {
                preOrderTraversalHelper(child, preOrderList);   
            }
        }     
    }    
}