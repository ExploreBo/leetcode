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
// recursion
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        helper(root, result);
        return result;
    }
    
    private void helper(TreeNode node, List<Integer> result) {
        if (node != null) {
            helper(node.left, result);
            helper(node.right, result);
            result.add(node.val);
        }
    }
}

// non-recursion, handle peek in 3 ways
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }
        // to be the last exit node
        TreeNode pre = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null && peek.left != pre && peek.right != pre) {
                // left and right have not been explored
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != pre) {
                // left has been explored but not for right 
                stack.push(peek.right);
            } else {
                // leaf node, or left and right have been explored
                result.add(stack.pop().val);
                pre = peek;
            }
        }
        return result;
    }
}

// Combine linkedlist and addFirst
public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> result = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    if (root == null) return result;
    
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        result.addFirst(cur.val);
        if (cur.left != null) {
            stack.push(cur.left);
        }
        if (cur.right != null) {
            stack.push(cur.right);
        } 
    }
    return result;
}
