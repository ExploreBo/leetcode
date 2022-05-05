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
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
    
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}


// use -1 as flag of non-balanced tree, avoid repeated height calculation
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private int height(TreeNode root) {
        if (root == null)
            return 0;
        
        int leftHeight = height(root.left);
        if (leftHeight == -1)                       
            return -1;
        
        int rightHeight = height(root.right);
        if (rightHeight == -1)                      
            return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) 
            return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}