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
// iteration
class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        getMax(root);
        return max;
    }
    
    private int getMax(TreeNode root) {
        if (root == null) return 0;
        // if (root.left == null && root.right == null) max = Math.max(root.val, max);
        int leftMax = Math.max(getMax(root.left), 0);
        int rightMax = Math.max(getMax(root.right), 0);
            
        max = Math.max(max, leftMax + rightMax + root.val);

        return Math.max(leftMax, rightMax) + root.val;        
    }
}

// recursive 
class Solution {
    public int maxPathSum(TreeNode root) {
        int max = Integer.MIN_VALUE;
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack();
        Map<TreeNode, Integer> map = new HashMap();
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            // leaf node, update max with node.val if possible and pop from stack
            if (node.left == null && node.right == null) {
                stack.pop();
                max = Math.max(max, node.val);
                map.put(node, node.val);
            // when the subtrees of this node have been updated, update the max and pop from stack
            } else if (map.containsKey(node.left) || map.containsKey(node.right)) {
                int leftMax = Math.max(0, map.getOrDefault(node.left, 0));
                int rightMax = Math.max(0, map.getOrDefault(node.right, 0));
                max = Math.max(max, leftMax + rightMax + node.val);
                map.put(node, Math.max(leftMax, rightMax) + node.val);
                stack.pop();
            // DFS going from up to bottom, push the left and right node if not null    
            } else {
                if (node.left != null) stack.push(node.left);
                if (node.right != null) stack.push(node.right);
            }

        }
        return max;
    }
}