/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// BST iterator until encounter p.
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode curr = node;

            node = node.right;
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            if (curr == p) break;
        }
        return stack.isEmpty() ? null : stack.pop();        
    }
}

// recursive (discard a half based on comparing with current root)
class Solution {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		
		TreeNode ret = null;
		while (root != null) {
			// 1. current root is smaller or equal, definitely not in this branch
			if (root.val <= p.val) {
				root = root.right;
			// 2. current root is bigger, definitely in this branch				
			} else {
				// 2.1 root is 1 of the successors, although might not be the one
				ret = root;
				// 2.2 maybe we can find a closer successor in the left branch
				root = root.left;
			}
		}
		return ret;
	}
}	

// not using any binary search tree's properties while treat it as a normal binary tree.
// two cases: 1. p has right child 2. p doesn't have right child
class Solution {
    
    private TreeNode previous;
    private TreeNode inorderSuccessorNode;
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        
        // Case 1: We simply need to find the leftmost node in the subtree rooted at p.right.
        if (p.right != null) {
            
            TreeNode leftmost = p.right;
            
            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }
            
            this.inorderSuccessorNode = leftmost;
        } else {
            
            // Case 2: We need to perform the standard inorder traversal and keep track of the previous node.
            this.inorderCase2(root, p);
        }
        
        return this.inorderSuccessorNode;
    }
    
    private void inorderCase2(TreeNode node, TreeNode p) {
        
        if (node == null) {
            return;
        }
        
        // Recurse on the left side
        this.inorderCase2(node.left, p);
        
        // Check if previous is the inorder predecessor of node
        if (this.previous == p && this.inorderSuccessorNode == null) {
            this.inorderSuccessorNode = node;
            return;
        }
        
        // Keeping previous up-to-date for further recursions
        this.previous = node;
        
        // Recurse on the right side
        this.inorderCase2(node.right, p);
    }
}







