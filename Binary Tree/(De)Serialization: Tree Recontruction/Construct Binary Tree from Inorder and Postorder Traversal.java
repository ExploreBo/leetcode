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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length -1);
    }
    
    private TreeNode buildTree(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorderEnd]);
        int leftLength = 0;
        while (true) {
            if (inorder[inorderStart + leftLength] == root.val) {
                break;
            }
            leftLength++;
        }
        root.left = buildTree(inorder, postorder, inorderStart, inorderStart + leftLength - 1, postorderStart, postorderStart + leftLength - 1);
        root.right = buildTree(inorder, postorder, inorderStart + leftLength + 1, inorderEnd, postorderStart + leftLength, postorderEnd - 1);
        return root;
    }
}