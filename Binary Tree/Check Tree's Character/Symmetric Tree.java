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
// BFS. level order traversal. Each level should be a palindrome.
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            int length = queue.size();

            int[] temp = new int[length];
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.remove();
                if (node == null) {
                    temp[i] = Integer.MIN_VALUE;    
                } else {
                    temp[i] = node.val;
                    queue.add(node.left);
                    queue.add(node.right);
                }
                if (i >= length / 2 && temp[i] != temp[length - 1 - i]) {
                    return false;
                }
            }
        }
        return true;
    }
}


// recursion
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if (left == null || right == null)
            return left == right;
        if (left.val != right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
}