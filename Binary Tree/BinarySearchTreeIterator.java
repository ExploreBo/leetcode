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


// Solution 2: with O(h) memeory, h is the height of the tree
// Use the stack as the data structure instead of doing a complete inorder traversal
// the peak of the stack will always be the smallest number right now
public class BSTIterator {
    private final Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        
        // Traversal cur node's right branch
        TreeNode cur = node.right;
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        
        return node.val;
    }
}











