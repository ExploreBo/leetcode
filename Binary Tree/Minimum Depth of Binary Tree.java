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
// recursion. visit each node once.
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

/* iteration BFS. 
   in the worst case for a balanced tree we need to visit all nodes level by level up to the tree height, 
   that excludes the bottom level only. 
   This way we visit N/2 nodes, and thus the time complexity is O(N).
*/
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null)   return 0;
        int depth = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (isLeafNode(curr))   return depth;
                if (curr.left != null)  queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            depth += 1;
        }
        return -1;
    }
    
    private boolean isLeafNode(TreeNode curr) {
        return curr != null && curr.left == null && curr.right == null;
    }
}