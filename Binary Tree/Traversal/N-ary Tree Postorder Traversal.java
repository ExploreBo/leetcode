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
    public List<Integer> postorder(Node root) {
        List<Integer> postorderList = new ArrayList();
        postorderTraversalHelper(root, postorderList);
        return postorderList;         
    }
    
    private void postorderTraversalHelper(Node root, List<Integer> postorderList) {
        if (root != null) {
            for (Node child : root.children) {
                postorderTraversalHelper(child, postorderList);   
            }
            postorderList.add(root.val);
        }     
    }    
}
