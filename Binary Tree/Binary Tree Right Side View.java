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
// BFS: level order, right to left each level. add the first element to result in each level.
// actually the direction is not that important. from left to right is also fine and no difference.
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();
        
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return result;
    }
}

// DFS traverse the tree level by level, starting each time from the rightmost child.
class Solution {
    List<Integer> rightside = new ArrayList();
    
    public void helper(TreeNode node, int level) {
        if (level == rightside.size()) 
            rightside.add(node.val);
        
        if (node.right != null) 
            helper(node.right, level + 1);  
        if (node.left != null) 
            helper(node.left, level + 1);
    }    
    
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return rightside;
        
        helper(root, 0);
        return rightside;
    }
}