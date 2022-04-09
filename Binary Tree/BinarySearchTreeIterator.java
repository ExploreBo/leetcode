/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {
    Queue<Integer> q = new LinkedList();
    public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            q.add(node.val);
            if (node.right != null) {
                cur = node.right;
            }
        }
    }        
    
    public int next() {
        if (q.size() != 0) {
            return q.poll();
        } else {
            return Integer.MIN_VALUE;
        }
    }
    
    public boolean hasNext() {
        return q.size() != 0;
    }
}